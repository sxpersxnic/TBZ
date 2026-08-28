# SSRF (Server-Side Request Forgery) Demonstration

A comprehensive educational demonstration of SSRF vulnerabilities with 3 Node.js microservices running via Docker Compose.

## Overview

This demo shows how SSRF attacks work and how to mitigate them. It contains:

1. **Web App** (Port 3000) - Vulnerable application with SSRF endpoints
2. **Public API** (Port 3001) - Publicly accessible service
3. **Internal Secret Service** (Port 3002) - Internal-only service with sensitive data

## What is SSRF?

Server-Side Request Forgery (SSRF) is a vulnerability where an attacker tricks a server into making unintended HTTP requests. The vulnerable server acts as an intermediary for the attacker, allowing them to:

- Access internal services and APIs
- Retrieve sensitive information
- Scan internal networks
- Exploit internal services
- Access cloud metadata services
- Bypass firewalls and authentication

## Quick Start

### Prerequisites

- Docker
- Docker Compose

### Running the Demo

```bash
cd /Users/sxnic/01_Workspaces/01_Active/TBZ/m183-application-security/ssrf-demo

# Start all services
docker-compose up

# In another terminal, open the web app
open http://localhost:3000
```

### Expected Output

```
ssrf-web-app              |  Web App listening on http://localhost:3000
ssrf-public-api           |  Public API listening on http://localhost:3001
ssrf-internal-secret-service |  Internal Secret Service listening on http://localhost:3002
```

## Demo Services

### 1. Web App (Port 3000)

The vulnerable application with a React frontend and Node.js backend.

**Vulnerable Endpoints:**

- `POST /api/fetch` - **VULNERABLE** - Fetches any URL without validation
- `POST /api/fetch-safe` - Safe version with validation

**Features:**

- Interactive web UI to test SSRF
- Two endpoints (vulnerable and safe)
- Pre-filled example URLs for testing
- Real-time response display

### 2. Public API (Port 3001)

Publicly accessible service for demonstration.

**Endpoints:**

- `GET /api/data` - Returns public data
- `GET /api/users` - Returns public users
- `GET /health` - Health check
- `GET /api/info` - Service info

### 3. Internal Secret Service (Port 3002)

Internal service with sensitive data. Should NOT be accessible from outside.

**Endpoints:**

- `GET /api/secret` - Returns sensitive secrets
- `GET /api/admin` - Returns admin credentials
- `GET /api/credentials` - Returns database credentials
- `GET /health` - Health check

## Testing SSRF

### Via the Web UI (Easiest)

1. Open http://localhost:3000 in your browser
2. Try the example buttons to test different scenarios:
    - **Public API**: `http://api:3001/api/data` ✓ Works
    - **Internal Secret**: `http://internal-secret-service:3002/api/secret` 🔥 SSRF!
    - **Localhost**: `http://127.0.0.1:3000/api/info` ✓ Works

### Via curl (Terminal)

```bash
# Vulnerable endpoint - Access public API
curl -X POST http://localhost:3000/api/fetch \
  -H "Content-Type: application/json" \
  -d '{"url":"http://api:3001/api/data"}'

# Vulnerable endpoint - Access internal secret service (SSRF!)
curl -X POST http://localhost:3000/api/fetch \
  -H "Content-Type: application/json" \
  -d '{"url":"http://internal-secret-service:3002/api/secret"}'

# Safe endpoint - Should block internal service
curl -X POST http://localhost:3000/api/fetch-safe \
  -H "Content-Type: application/json" \
  -d '{"url":"http://internal-secret-service:3002/api/secret"}'
```

## What the Demo Demonstrates

### 1. Vulnerable Endpoint (SSRF Exploitation)

The `/api/fetch` endpoint has **NO validation**:

```javascript
app.post('/api/fetch', async (req, res) => {
	const { url } = req.body;
	// VULNERABLE: No validation!
	const response = await axios.get(url);
	res.json(response.data);
});
```

**Attack scenario:**

- Attacker sends: `http://internal-secret-service:3002/api/secret`
- The web server fetches the internal service
- Sensitive data is returned to the attacker

### 2. Safe Endpoint (SSRF Mitigation)

The `/api/fetch-safe` endpoint implements validation:

```javascript
app.post('/api/fetch-safe', async (req, res) => {
	const { url } = req.body;
	const urlObj = new URL(url);

	// ✓ Validate and reject private IP ranges
	const privateRanges = [
		/^127\./, // 127.0.0.0/8
		/^10\./, // 10.0.0.0/8
		/^172\.(1[6-9]|2[0-9]|3[0-1])\./, // 172.16.0.0/12
		/^192\.168\./, // 192.168.0.0/16
	];

	if (privateRanges.some((range) => range.test(urlObj.hostname))) {
		return res
			.status(403)
			.json({ error: 'Access to private networks is forbidden' });
	}

	const response = await axios.get(url);
	res.json(response.data);
});
```

**Result:** Attempting to access internal services fails with 403 Forbidden

## SSRF Attack Vectors

This demo covers common SSRF scenarios:

1. **Direct Internal Service Access**
    - `http://internal-secret-service:3002/api/secret`
    - `http://internal-db.local:5432`

2. **Localhost/Loopback Addresses**
    - `http://127.0.0.1:3000`
    - `http://localhost:8080`
    - `http://[::1]:3000` (IPv6)

3. **Private IP Ranges**
    - `http://10.0.0.1`
    - `http://172.16.0.1`
    - `http://192.168.1.1`

## SSRF Mitigation Strategies

### 1. ✓ Whitelist Allowed URLs

Only allow specific, pre-approved URLs:

```javascript
const allowedUrls = ['https://api.example.com', 'https://data.example.com'];
if (!allowedUrls.includes(url)) {
	return res.status(403).json({ error: 'URL not allowed' });
}
```

### 2. ✓ Block Private IP Ranges

Reject requests to private/internal IP addresses:

```javascript
const isPrivate = privateRanges.some((range) => range.test(hostname));
if (isPrivate) {
	return res
		.status(403)
		.json({ error: 'Access to private networks is forbidden' });
}
```

### 3. ✓ Disable HTTP Redirects

Prevent attackers from using redirects to bypass filters:

```javascript
axios.get(url, { maxRedirects: 0 });
```

### 4. ✓ Network Segmentation

Keep internal services on isolated networks:

```yaml
networks:
    ssrf-network:
        driver: bridge
```

### 5. ✓ Use DNS Rebinding Protection

Validate DNS responses to prevent rebinding attacks.

### 6. ✓ Rate Limiting

Detect unusual request patterns:

```javascript
app.use(rateLimit({ windowMs: 15 * 60 * 1000, max: 100 }));
```

### 7. ✓ Input Validation

Validate and sanitize all user inputs:

```javascript
const url = new URL(userInput); // Throws if invalid
```

### 8. ✓ Cloud Metadata Protection

Disable access to cloud metadata services:

- AWS: `http://169.254.169.254/latest/meta-data/`
- GCP: `http://metadata.google.internal/`
- Azure: `http://169.254.169.254/metadata/`

## Security Notes

**Important:**

- This is a **deliberately vulnerable application** for educational purposes only
- Never use this code in production
- The internal secret service intentionally exposes secrets for demonstration
- All passwords/keys shown are fake and for demo purposes only

## File Structure

```
ssrf-demo/
├── docker-compose.yaml          # Service orchestration
├── README.md                      # This file
├── web/                           # Vulnerable web app
│   ├── Dockerfile
│   ├── package.json
│   ├── server.js                  # Express backend
│   └── public/
│       └── index.html             # React frontend
├── api/                           # Public API
│   ├── Dockerfile
│   ├── package.json
│   └── server.js
└── internal-secret-service/       # Internal secrets
    ├── Dockerfile
    ├── package.json
    └── server.js
```

## Ports

| Service                 | Port | Purpose                                    |
| ----------------------- | ---- | ------------------------------------------ |
| Web App                 | 3000 | Vulnerable application with SSRF endpoints |
| Public API              | 3001 | Publicly accessible API                    |
| Internal Secret Service | 3002 | Internal-only service (target of SSRF)     |

## Stopping the Demo

```bash
# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v

# Remove all images
docker-compose down --rmi all
```

## Learning Resources

### What to Try:

1. **Basic SSRF Attack**
    - Access the web app at http://localhost:3000
    - In the "Vulnerable (SSRF)" tab
    - Click "→ Internal Secret (SSRF!) 🔥"
    - See how the backend fetches the internal service

2. **Comparing Vulnerable vs Safe**
    - Same URL in vulnerable endpoint: ✓ Works
    - Same URL in safe endpoint: ✗ Blocked
    - Understand the difference

3. **Different Attack Vectors**
    - Try `http://127.0.0.1:3000` (localhost)
    - Try `http://10.0.0.1` (private IP)
    - Try variations and understand blocking

4. **Cloud Metadata Service**
    - Uncomment the example in the UI
    - Try accessing AWS metadata: `http://169.254.169.254/latest/meta-data/`
    - See how SSRF can leak cloud credentials

## Troubleshooting

### Services won't start

```bash
# Check logs
docker-compose logs

# Check specific service
docker-compose logs web

# Rebuild images
docker-compose down --rmi all
docker-compose up --build
```

### Cannot access localhost:3000

```bash
# Check if services are running
docker ps

# Check port conflicts
lsof -i :3000
```

### Network connectivity issues

```bash
# Inspect network
docker network ls
docker network inspect m183-ssrf-demo_ssrf-network
```

## Advanced Testing

### Using Docker exec to test from inside containers

```bash
# From web container to public API
docker exec ssrf-web-app curl http://api:3001/api/data

# From web container to internal service
docker exec ssrf-web-app curl http://internal-secret-service:3002/api/secret
```

### Monitoring requests

```bash
# Watch web service logs
docker-compose logs -f web

# Watch internal secret service logs
docker-compose logs -f internal-secret-service
```

## Additional Examples

### Testing with different payloads

```bash
# File URL (may work on some systems)
curl -X POST http://localhost:3000/api/fetch \
  -d '{"url":"file:///etc/passwd"}'

# Gopher protocol (old protocol, may still be enabled)
curl -X POST http://localhost:3000/api/fetch \
  -d '{"url":"gopher://internal-cache:6379"}'

# HTTPS (if certificate is self-signed)
curl -X POST http://localhost:3000/api/fetch \
  -d '{"url":"https://internal-service:8443/api/secret"}'
```

## References

- [OWASP SSRF](https://owasp.org/www-community/attacks/Server-Side_Request_Forgery)
- [PortSwigger SSRF](https://portswigger.net/web-security/ssrf)
- [CWE-918: Server-Side Request Forgery (SSRF)](https://cwe.mitre.org/data/definitions/918.html)
