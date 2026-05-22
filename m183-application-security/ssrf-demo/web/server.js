import axios from 'axios';
import cors from 'cors';
import express from 'express';
import path from 'path';
import { fileURLToPath } from 'url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const app = express();

app.use(cors());
app.use(express.json());
app.use(express.static('public'));

// Serve index.html for the React app
app.get('/', (req, res) => {
	res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// VULNERABLE ENDPOINT - This is susceptible to SSRF attacks
// The vulnerability: User input is directly used in a request without validation
app.post('/api/fetch', async (req, res) => {
	try {
		const { url } = req.body;

		// NO VALIDATION - This is intentionally vulnerable!
		// In a real app, you should:
		// 1. Whitelist allowed URLs
		// 2. Reject private/internal IP ranges (10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/16, 127.0.0.0/8)
		// 3. Use a proxy/firewall
		// 4. Disable access to metadata services (AWS, GCP, etc.)

		if (!url) {
			return res.status(400).json({ error: 'URL is required' });
		}

		console.log(`[VULNERABLE] Fetching URL: ${url}`);

		const response = await axios.get(url, {
			timeout: 5000,
			maxRedirects: 0,
			headers: {
				'User-Agent': 'SSRF-Demo/1.0',
			},
		});

		res.json({
			success: true,
			status: response.status,
			headers: response.headers,
			data: response.data,
		});
	} catch (error) {
		console.error('[ERROR]', error.message);
		res.status(400).json({
			error: error.message,
			data: error.response?.data || null,
		});
	}
});

// Safe endpoint - demonstrates proper validation
app.post('/api/fetch-safe', async (req, res) => {
	try {
		const { url } = req.body;

		if (!url) {
			return res.status(400).json({ error: 'URL is required' });
		}

		// Validate URL
		const urlObj = new URL(url);

		// Reject private IP ranges
		const hostname = urlObj.hostname;
		const privateRanges = [
			/^127\./, // 127.0.0.0/8
			/^10\./, // 10.0.0.0/8
			/^172\.(1[6-9]|2[0-9]|3[0-1])\./, // 172.16.0.0/12
			/^192\.168\./, // 192.168.0.0/16
			/^localhost$/, // localhost
			/^.*\.internal$/, // internal domains
			/^internal-/, // internal service names
		];

		const isPrivate = privateRanges.some((range) => range.test(hostname));

		if (isPrivate) {
			return res
				.status(403)
				.json({ error: 'Access to private networks is forbidden' });
		}

		console.log(`[SAFE] Fetching URL: ${url}`);

		const response = await axios.get(url, {
			timeout: 5000,
			maxRedirects: 0,
			headers: {
				'User-Agent': 'SSRF-Demo/1.0',
			},
		});

		res.json({
			success: true,
			status: response.status,
			headers: response.headers,
			data: response.data,
		});
	} catch (error) {
		console.error('[ERROR]', error.message);
		res.status(400).json({
			error: error.message,
			data: error.response?.data || null,
		});
	}
});

// Info endpoint
app.get('/api/info', (req, res) => {
	res.json({
		service: 'Web App (Vulnerable)',
		description: 'Demonstrates SSRF vulnerability',
		endpoints: [
			{
				method: 'POST',
				path: '/api/fetch',
				description: 'VULNERABLE - Fetch any URL',
			},
			{
				method: 'POST',
				path: '/api/fetch-safe',
				description: 'SAFE - Fetch URL with validation',
			},
		],
	});
});

const PORT = 3000;
app.listen(PORT, () => {
	console.log(`[WEB APP] Listening on http://localhost:${PORT}`);
	console.log(
		`[WEB APP] Endpoints: /api/fetch (vulnerable), /api/fetch-safe (protected), /api/info`,
	);
});

