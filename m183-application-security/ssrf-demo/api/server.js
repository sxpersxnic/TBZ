import cors from 'cors';
import express from 'express';

const app = express();

app.use(cors());
app.use(express.json());

// Public data endpoint
app.get('/api/data', (req, res) => {
	console.log('[PUBLIC API] GET /api/data');
	res.json({
		service: 'Public API',
		message: 'This is publicly accessible data',
		timestamp: new Date().toISOString(),
		data: [
			{ id: 1, name: 'Item 1', value: 'Public value 1' },
			{ id: 2, name: 'Item 2', value: 'Public value 2' },
			{ id: 3, name: 'Item 3', value: 'Public value 3' },
		],
	});
});

// Public users endpoint
app.get('/api/users', (req, res) => {
	console.log('[PUBLIC API] GET /api/users');
	res.json({
		service: 'Public API',
		users: [
			{ id: 1, username: 'alice', email: 'alice@example.com' },
			{ id: 2, username: 'bob', email: 'bob@example.com' },
			{ id: 3, username: 'charlie', email: 'charlie@example.com' },
		],
	});
});

// Health check
app.get('/health', (req, res) => {
	res.json({ status: 'ok', service: 'Public API' });
});

// Info endpoint
app.get('/api/info', (req, res) => {
	res.json({
		service: 'Public API',
		description: 'Publicly accessible API service',
		endpoints: [
			{
				method: 'GET',
				path: '/api/data',
				description: 'Get public data',
			},
			{
				method: 'GET',
				path: '/api/users',
				description: 'Get public users',
			},
			{ method: 'GET', path: '/health', description: 'Health check' },
		],
	});
});

const PORT = 3001;
app.listen(PORT, () => {
	console.log(`[PUBLIC API] Listening on http://localhost:${PORT}`);
	console.log(
		`[PUBLIC API] Endpoints: /api/data, /api/users, /health, /api/info`,
	);
});

