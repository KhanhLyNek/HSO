#!/data/data/com.termux/files/usr/bin/bash
# Cài HSO trên Termux và tạo lệnh tắt `hso`.
#   curl -fsSL https://raw.githubusercontent.com/KhanhLyNek/HSO/main/install.sh | bash
set -e
REPO="https://github.com/KhanhLyNek/HSO.git"
HSO_DIR="$HOME/HSO"
BIN="${PREFIX:-/data/data/com.termux/files/usr}/bin/hso"

echo "== Cài gói cần thiết =="
pkg install -y git python >/dev/null 2>&1 || { pkg update -y && pkg install -y git python; }

if [ -d "$HSO_DIR/.git" ]; then
    echo "== Đã có $HSO_DIR, cập nhật =="
    git -C "$HSO_DIR" pull --ff-only || true
else
    echo "== Tải repo về $HSO_DIR =="
    git clone "$REPO" "$HSO_DIR"
fi

echo "== Tạo lệnh $BIN =="
cat > "$BIN" <<EOF
#!${PREFIX:-/data/data/com.termux/files/usr}/bin/bash
# Lệnh tắt HSO: tự cập nhật từ GitHub rồi chạy.
HSO_DIR="\${HSO_DIR:-$HSO_DIR}"
if [ ! -d "\$HSO_DIR/.git" ]; then
    git clone "$REPO" "\$HSO_DIR" || exit 1
fi
echo "== Cập nhật HSO từ GitHub =="
if timeout 20 git -C "\$HSO_DIR" pull --ff-only --quiet; then
    echo "   Bản hiện tại: \$(git -C "\$HSO_DIR" log -1 --format='%h %s')"
else
    echo "   (Không cập nhật được: mất mạng hoặc file trong máy đã bị sửa. Chạy bản đang có.)"
fi
exec bash "\$HSO_DIR/hso.sh" "\$@"
EOF
chmod +x "$BIN" "$HSO_DIR/hso.sh"
cat > "${BIN}log" <<EOF
#!${PREFIX:-/data/data/com.termux/files/usr}/bin/bash
HSO_DIR="\${HSO_DIR:-$HSO_DIR}"
timeout 20 git -C "\$HSO_DIR" pull --ff-only --quiet 2>/dev/null
exec bash "\$HSO_DIR/hso.sh" log "\$@"
EOF
chmod +x "${BIN}log"

echo
echo "Xong! Gõ:  hso         -> cập nhật + chạy server Thỏ Mika"
echo "           hsolog      -> đẩy log server + J2ME lên GitHub"
echo "           hso patch   -> vá file game (cần KnightOnline_v403.jar)"
echo "           hso help    -> xem các lệnh"
