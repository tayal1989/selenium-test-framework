# Docker Configuration for Selenium Test Framework

This directory contains Docker configuration files for running Selenium tests in containerized environments.

## Files Overview

### `Dockerfile`
Main Docker image configuration for the Selenium test framework.
- Based on Maven with OpenJDK 17
- Includes Chrome browser and dependencies
- Optimized for test execution

### `docker-compose.selenium-grid.yml`
Advanced Selenium Grid setup with multiple browser nodes:
- Hub and spoke architecture
- Multiple Chrome, Firefox, and Edge nodes
- Scalable configuration for parallel testing

## Usage

### Basic Standalone Chrome
```bash
# From project root
docker-compose up selenium-chrome
```

### Full Selenium Grid
```bash
# From project root
docker-compose -f docker/docker-compose.selenium-grid.yml up

# With Edge nodes
docker-compose -f docker/docker-compose.selenium-grid.yml --profile full up
```

### Build Custom Test Image
```bash
# From project root
docker build -f docker/Dockerfile -t selenium-test-framework .
```

## Configuration

### Environment Variables
- `SE_NODE_MAX_SESSIONS`: Maximum concurrent sessions per node
- `SE_NODE_SESSION_TIMEOUT`: Session timeout in seconds
- `GRID_MAX_SESSION`: Maximum sessions for the hub
- `GRID_BROWSER_TIMEOUT`: Browser timeout in seconds

### Ports
- `4444`: Selenium Hub/Standalone WebDriver
- `7900`: VNC Server (password: secret)

### Volumes
- `/dev/shm`: Shared memory for browser stability
- `./target`: Test reports and artifacts
- `./logs`: Application logs

## Scaling

### Scale Chrome Nodes
```bash
docker-compose -f docker/docker-compose.selenium-grid.yml up --scale chrome-node=4
```

### Scale Firefox Nodes
```bash
docker-compose -f docker/docker-compose.selenium-grid.yml up --scale firefox-node=2
```

## Monitoring

### Grid Console
http://localhost:4444

### VNC Viewer
http://localhost:7900 (password: secret)

## Troubleshooting

### Common Issues
1. **Platform Compatibility**: Use `platform: linux/amd64` for Apple Silicon Macs
2. **Memory Issues**: Increase `shm_size` for browser stability
3. **Connection Issues**: Ensure containers are on the same network

### Logs
```bash
# View container logs
docker logs selenium-chrome
docker logs selenium-hub

# Follow logs
docker logs -f selenium-chrome
```

### Health Checks
```bash
# Check hub status
curl http://localhost:4444/wd/hub/status

# Check grid readiness
curl http://localhost:4444/wd/hub/status | jq '.value.ready'
```
