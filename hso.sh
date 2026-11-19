#!/data/data/com.termux/files/usr/bin/bash
# Lệnh chính của HSO (được gọi bởi lệnh `hso` sau khi đã git pull).
#   hso                 -> chạy server Thỏ Mika (cổng 19129)
#   hso sniff <host>    -> proxy bắt gói tin tới server thật
#   hso patch [ip]      -> vá lại KnightOnline_v403.jar -> KO_ThoMika.jar
#   hso test            -> chạy client giả lập để kiểm tra server
#   hso help

HSO_DIR="${HSO_DIR:-$HOME/HSO}"
cd "$HSO_DIR" || exit 1

find_jar() {
    for f in "$HSO_DIR/KnightOnline_v403.jar" \
             "$HOME/storage/downloads/KnightOnline_v403.jar" \
             "/sdcard/Download/KnightOnline_v403.jar"; do
        [ -f "$f" ] && { echo "$f"; return 0; }
    done
    return 1
}

cmd="${1:-host}"
[ $# -gt 0 ] && shift

case "$cmd" in
    host|run|start)
        echo "== Server Thỏ Mika (Ctrl+C để dừng) =="
        exec python server.py "$@"
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
        sed -n '2,8p' "$0" | sed 's/^# \{0,1\}//'
        ;;
    *)
        echo "Lệnh không rõ: $cmd  (gõ: hso help)"; exit 1
        ;;
esac
