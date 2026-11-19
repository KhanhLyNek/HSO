#!/data/data/com.termux/files/usr/bin/bash
# Lệnh chính của HSO (được gọi bởi lệnh `hso` sau khi đã git pull).
#   hso                 -> chạy server Thỏ Mika (cổng 19129), ghi log, tắt thì tự đẩy log
#   hsolog  (= hso log) -> đẩy log server + log J2ME Loader lên GitHub (nhánh logs)
#   hso token           -> nhập/đổi GitHub token dùng để đẩy log
#   hso sniff <host>    -> proxy bắt gói tin tới server thật
#   hso patch [ip]      -> vá lại KnightOnline_v403.jar -> KO_ThoMika.jar
#   hso test            -> chạy client giả lập để kiểm tra server
#   hso help

HSO_DIR="${HSO_DIR:-$HOME/HSO}"
PREFIX="${PREFIX:-/data/data/com.termux/files/usr}"
CONF_DIR="$HOME/.hso"
TOKEN_FILE="$CONF_DIR/token"
LOG_DIR="$HSO_DIR/logs"
REPO_PATH="KhanhLyNek/HSO"
J2ME_DIRS="/sdcard/J2ME-Loader /storage/emulated/0/J2ME-Loader $HOME/storage/shared/J2ME-Loader"
cd "$HSO_DIR" || exit 1
mkdir -p "$LOG_DIR" "$CONF_DIR"

# ---- tự cài lệnh tắt `hsolog` (cho máy đã cài từ bản cũ) ----
if [ ! -x "$PREFIX/bin/hsolog" ] && [ -d "$PREFIX/bin" ]; then
    cat > "$PREFIX/bin/hsolog" <<EOF
#!$PREFIX/bin/bash
HSO_DIR="\${HSO_DIR:-$HSO_DIR}"
timeout 20 git -C "\$HSO_DIR" pull --ff-only --quiet 2>/dev/null
exec bash "\$HSO_DIR/hso.sh" log "\$@"
EOF
    chmod +x "$PREFIX/bin/hsolog"
    echo "(Đã tạo lệnh hsolog)"
fi

find_jar() {
    for f in "$HSO_DIR/KnightOnline_v403.jar" \
             "$HOME/storage/downloads/KnightOnline_v403.jar" \
             "/sdcard/Download/KnightOnline_v403.jar"; do
        [ -f "$f" ] && { echo "$f"; return 0; }
    done
    return 1
}

ask_token() {
    echo "== Cần GitHub token để đẩy log =="
    echo "Tạo tại: https://github.com/settings/personal-access-tokens/new"
    echo "  - Repository access: Only select repositories -> HSO"
    echo "  - Permissions: Contents -> Read and write"
    printf "Dán token vào đây (không hiện ra): "
    read -rs tok; echo
    [ -z "$tok" ] && { echo "Bỏ qua."; return 1; }
    umask 077; printf '%s' "$tok" > "$TOKEN_FILE"
    echo "Đã lưu token vào $TOKEN_FILE"
}

push_logs() {
    # $1 = "auto" -> không hỏi token, im lặng nếu chưa có
    if [ ! -s "$TOKEN_FILE" ]; then
        [ "$1" = "auto" ] && { echo "(Chưa có token: gõ 'hso token' để bật tự đẩy log)"; return 0; }
        ask_token || return 1
    fi
    local tok; tok="$(cat "$TOKEN_FILE")"
    local url="${HSO_LOG_URL:-https://hso:${tok}@github.com/${REPO_PATH}.git}"
    local stamp; stamp="$(date +%Y%m%d-%H%M%S)"
    local work="$CONF_DIR/logs-repo"

    echo "== Đẩy log lên GitHub (nhánh logs) =="
    if [ ! -d "$work/.git" ]; then
        rm -rf "$work"; git init -q "$work"
    fi
    cd "$work" || return 1
    git config user.name "hso-log"; git config user.email "hso-log@users.noreply.github.com"
    if timeout 30 git fetch -q --depth 1 "$url" logs 2>/dev/null; then
        git checkout -q -B logs FETCH_HEAD
    else
        git checkout -q --orphan logs 2>/dev/null || git checkout -q logs
        git rm -rq --cached . 2>/dev/null; rm -rf ./*
        echo "# Log HSO - đẩy tự động bằng hsolog" > README.md
    fi

    rm -rf latest; mkdir -p latest "history/$stamp"
    # log server (bản mới nhất)
    local srv; srv="$(ls -t "$LOG_DIR"/server-*.log 2>/dev/null | head -1)"
    [ -n "$srv" ] && cp "$srv" latest/server.log
    # log J2ME Loader (Lưu log trong menu game) + crash
    local found=0
    for d in $J2ME_DIRS; do
        [ -f "$d/log.txt" ]   && { cp "$d/log.txt"   latest/j2me-log.txt; found=1; }
        [ -f "$d/crash.txt" ] && cp "$d/crash.txt" latest/j2me-crash.txt
        [ $found = 1 ] && break
    done
    {
        echo "thoi_gian: $(date '+%F %T %z')"
        echo "hso: $(git -C "$HSO_DIR" log -1 --format='%h %s')"
        echo "python: $(python --version 2>&1)"
        echo "android: $(getprop ro.build.version.release 2>/dev/null) / $(getprop ro.product.model 2>/dev/null)"
        echo "server_log: ${srv:-khong co}"
        echo "j2me_log: $([ $found = 1 ] && echo co || echo 'khong thay (trong game: menu -> Lưu log; Termux: termux-setup-storage)')"
    } > latest/info.txt
    cp -r latest/. "history/$stamp/"
    git add -A
    git commit -qm "log $stamp" || { echo "Không có gì mới."; cd "$HSO_DIR"; return 0; }
    if timeout 60 git push -q "$url" logs:logs 2>&1 | sed "s/${tok}/***/g"; then
        if git ls-remote "$url" logs 2>/dev/null | grep -q "$(git rev-parse HEAD)"; then
            echo "Đã đẩy: https://github.com/${REPO_PATH}/tree/logs/latest"
        else
            echo "Đẩy thất bại (token sai/hết hạn? gõ 'hso token' để nhập lại)"
        fi
    fi
    cd "$HSO_DIR"
}

cmd="${1:-host}"
[ $# -gt 0 ] && shift

case "$cmd" in
    host|run|start)
        LOG="$LOG_DIR/server-$(date +%Y%m%d-%H%M%S).log"
        ls -t "$LOG_DIR"/server-*.log 2>/dev/null | tail -n +20 | xargs -r rm -f   # giữ 20 file
        echo "== Server Thỏ Mika (Ctrl+C để dừng) =="
        echo "   log: $LOG"
        trap ':' INT    # Ctrl+C chỉ dừng server, script chạy tiếp để đẩy log
        python -u server.py "$@" 2>&1 | tee -i "$LOG"
        trap - INT
        push_logs auto
        ;;
    log|logs)
        push_logs
        ;;
    token)
        ask_token
        ;;
    sniff)
        [ -z "$1" ] && { echo "Dùng: hso sniff <host-server-thật>"; exit 1; }
        up="$1"; shift
        exec python sniffer.py --upstream "$up" "$@"
        ;;
    patch)
        jar="$(find_jar)" || { echo "Không thấy KnightOnline_v403.jar (để trong ~/HSO hoặc Download)"; exit 1; }
        ip="${1:-127.0.0.1}"
        out="$HSO_DIR/KO_ThoMika.jar"
        python patch_jar.py "$jar" "$out" --ip "$ip" || exit 1
        if [ -d /sdcard/Download ] && cp "$out" /sdcard/Download/ 2>/dev/null; then
            echo "Đã chép KO_ThoMika.jar vào /sdcard/Download/"
        fi
        ;;
    test)
        exec python test_client.py "${1:-127.0.0.1}" "${2:-19129}"
        ;;
    help|-h|--help)
        sed -n '2,10p' "$0" | sed 's/^# \{0,1\}//'
        ;;
    *)
        echo "Lệnh không rõ: $cmd  (gõ: hso help)"; exit 1
        ;;
esac
