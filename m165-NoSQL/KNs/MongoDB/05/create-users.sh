#!/usr/bin/sh

# Admin credentials
ADMIN_USER='admin'
ADMIN_PASS=${1}
AUTH_DB='admin'

TARGET_DB='band_mgmt'

# User1
USER1="read_only_user"
USER1_PASS="read_only_pass"

echo "[+] Creating ${USER1} in MongoDB"
mongosh --authenticationDatabase "${AUTH_DB}" -u "${ADMIN_USER}" -p "${ADMIN_PASS}" <<EOF >
use ${TARGET_DB};
db.createUser({
	user: "${USER1}",
	pwd: "${USER1_PASS}",
	roles: [
		{ role: "read", db: "${TARGET_DB}" },
	]
})
EOF

# User2
USER2="read_write_user"
USER2_PASS="read_write_pass"

echo "[+] Creating ${USER2} in MongoDB"
mongosh --authenticationDatabase "${AUTH_DB}" -u "${ADMIN_USER}" -p "${ADMIN_PASS}" <<EOF >
use ${AUTH_DB};
db.createUser({
	user: "${USER2}",
	pwd: "${USER2_PASS}",
	roles: [
		{ role: "readWrite", db: "${TARGET_DB}" },
	]
})
EOF
