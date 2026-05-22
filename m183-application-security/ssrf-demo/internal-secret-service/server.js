import cors from 'cors';
import express from 'express';

const app = express();

app.use(cors());
app.use(express.json());

// IMPORTANT: This service is only accessible internally (via SSRF attack)
// In a real deployment, this would be on a private network without internet access

// Secret endpoint - should NOT be accessible from outside
app.get('/api/secret', (req, res) => {
	console.log('[INTERNAL SECRET SERVICE] GET /api/secret - ACCESSED!');
	res.json({
		service: 'Internal Secret Service',
		alert: 'INTERNAL SERVICE ACCESSED - SECURITY RISK!',
		timestamp: new Date().toISOString(),
		secrets: [
			{
				id: 1,
				title: 'Database Password',
				value: 'super_secret_db_password_12345',
				level: 'CRITICAL',
			},
			{
				id: 2,
				title: 'API Keys',
				value: 'sk_live_abcd1234efgh5678ijkl9012mnop3456',
				level: 'CRITICAL',
			},
			{
				id: 3,
				title: 'Private Keys',
				value: '-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBg...\n-----END PRIVATE KEY-----',
				level: 'CRITICAL',
			},
			{
				id: 4,
				title: 'Internal Configuration',
				value: 'db_host=internal-db.local, cache_host=internal-cache.local',
				level: 'HIGH',
			},
		],
	});
});

// Admin endpoint - super secret
app.get('/api/admin', (req, res) => {
	console.log('[INTERNAL SECRET SERVICE] GET /api/admin - ACCESSED!');
	res.json({
		service: 'Internal Secret Service',
		alert: 'ADMIN ENDPOINT ACCESSED - CRITICAL SECURITY BREACH!',
		adminUsers: [
			{
				id: 1,
				username: 'admin',
				password_hash: 'hash_of_super_secret_password',
			},
			{ id: 2, username: 'root', password_hash: 'hash_of_root_password' },
		],
		systemConfig: {
			debug_mode: true,
			log_level: 'DEBUG',
			internal_endpoints: [
				'http://internal-db.local:5432',
				'http://internal-cache.local:6379',
				'http://internal-queue.local:5672',
			],
		},
	});
});

// Database credentials endpoint
app.get('/api/credentials', (req, res) => {
	console.log('[INTERNAL SECRET SERVICE] GET /api/credentials - ACCESSED!');
	res.json({
		service: 'Internal Secret Service',
		databases: [
			{
				name: 'production',
				host: 'internal-db.local',
				port: 5432,
				username: 'db_user',
				password: 'P@ssw0rd_Database_123!',
				name: 'prod_database',
			},
			{
				name: 'backup',
				host: 'internal-db-backup.local',
				port: 5432,
				username: 'db_backup_user',
				password: 'B@ckup_Pass_456!',
				database: 'backup_database',
			},
		],
		cache: {
			host: 'internal-cache.local',
			port: 6379,
			password: 'Cache_Secret_789!',
		},
	});
});

// Health check
app.get('/health', (req, res) => {
	res.json({
		status: 'ok',
		service: 'Internal Secret Service',
		environment: 'internal_only',
	});
});

// Info endpoint
app.get('/api/info', (req, res) => {
	res.json({
		service: 'Internal Secret Service',
		description:
			'INTERNAL USE ONLY - Should not be accessible from outside',
		warning:
			'This service should only be accessible from within the internal network!',
		endpoints: [
			{
				method: 'GET',
				path: '/api/secret',
				description: 'Sensitive secrets',
			},
			{
				method: 'GET',
				path: '/api/admin',
				description: 'Admin credentials',
			},
			{
				method: 'GET',
				path: '/api/credentials',
				description: 'Database credentials',
			},
			{ method: 'GET', path: '/health', description: 'Health check' },
		],
	});
});

const PORT = 3002;
app.listen(PORT, () => {
	console.log(
		`[INTERNAL SECRET SERVICE] Listening on http://localhost:${PORT}`,
	);
	console.log(
		`[INTERNAL SECRET SERVICE] WARNING: This service should NOT be accessible from outside!`,
	);
});

