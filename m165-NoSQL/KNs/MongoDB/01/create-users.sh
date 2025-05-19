#!/usr/bin/sh

# Admin credentials
ADMIN_USER='admin'
ADMIN_PASS=${1}
AUTH_DB='admin'

TARGET_DB='Kampus'

# User1
USER1="user1"
USER1_PASS="user1pass"

echo "[+] Creating user1 in MongoDB"
mongosh --authenticationDatabase "${AUTH_DB}" -u "${ADMIN_USER}" -p "${ADMIN_PASS}" <<EOF >
use ${TARGET_DB}
db.createUser({
	user: "${USER1}",
	pwd: "${USER1_PASS}",
	roles: [
		{ role: "read", db: "${TARGET_DB}" },
	]
})
EOF

# User2
USER2="user2"
USER2_PASS="user2pass"

echo "[+] Creating user2 in MongoDB"
mongosh --authenticationDatabase "${AUTH_DB}" -u "${ADMIN_USER}" -p "${ADMIN_PASS}" <<EOF >
use ${AUTH_DB}
db.createUser({
	user: "${USER2}",
	pwd: "${USER2_PASS}",
	roles: [
		{ role: "readWrite", db: "${TARGET_DB}" },
	]
})
EOF

