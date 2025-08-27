# Docker Setup for Selenium Test Framework

## 📁 Folder Structure

```
selenium-test-framework/
├── 📂 docker/                          # Docker configuration files
│   ├── Dockerfile                      # Main container definition
│   ├── docker-compose.selenium-grid.yml # Advanced Grid setup
│   └── README.md                       # Docker documentation
├── 📂 scripts/                         # Automation scripts
│   ├── start-selenium.sh              # Start Selenium container
│   ├── stop-selenium.sh               # Stop Selenium container
│   ├── run-tests.sh                   # Run tests with Docker
│   └── docker-test-runner.sh          # Legacy comprehensive runner
├── 📂 src/test/java/
│   ├── 📂 config/
│   │   └── RemoteWebDriverConfig.java # Docker WebDriver configuration
│   └── 📂 actual_test/
│       ├── DockerDemoTest.java        # Docker integration demo
│       └── DockerSeleniumTest.java    # Docker Selenium examples
├── docker-compose.yml                 # Main Docker Compose file
├── .dockerignore                      # Docker ignore patterns
└── run-docker-tests                   # Main script (ROOT LEVEL)
```

## 🚀 Quick Start

### 1. Start Selenium Container
```bash
./run-docker-tests start
```

### 2. Run Tests
```bash
# Run demo tests
./run-docker-tests test

# Run specific test class
./run-docker-tests test GoogleSearchTest
```

### 3. Watch Tests in Action
```bash
./run-docker-tests vnc
```

### 4. Check Status
```bash
./run-docker-tests status
```

### 5. Stop Container
```bash
./run-docker-tests stop
```

## 🔧 Advanced Usage

### Docker Compose (Recommended)
```bash
# Start standalone Chrome
docker-compose up selenium-chrome

# Start full test suite
docker-compose up selenium-tests

# Advanced Grid with multiple browsers
docker-compose -f docker/docker-compose.selenium-grid.yml up
```

### Individual Scripts
```bash
# Fine-grained control
./scripts/start-selenium.sh
./scripts/run-tests.sh DockerDemoTest
./scripts/stop-selenium.sh
```

## 🌐 Access Points

| Service | URL | Purpose |
|---------|-----|---------|
| VNC Viewer | http://localhost:7900 | Watch tests run (password: secret) |
| Selenium Grid | http://localhost:4444 | Grid console and status |
| WebDriver Endpoint | http://localhost:4444/wd/hub | For test configuration |

## 📊 Test Classes

| Test Class | Purpose |
|------------|---------|
| `DockerDemoTest` | Basic Docker Selenium functionality |
| `DockerSeleniumTest` | Google search examples |
| `GoogleSearchTest` | Legacy local test (can be adapted) |

## 🛠️ Configuration

### Environment Variables
- `selenium.remote=true` - Use Docker container instead of local browser
- `jacoco.skip=true` - Skip code coverage (faster execution)

### Maven Commands
```bash
# Run with Docker
mvn test -Dtest=DockerDemoTest -Dselenium.remote=true -Djacoco.skip=true

# Run locally (if local drivers available)
mvn test -Dtest=DockerDemoTest -Dselenium.remote=false
```

## 🔍 Troubleshooting

### Container Not Starting
```bash
# Check Docker status
docker ps -a

# View logs
./run-docker-tests logs

# Clean and restart
./run-docker-tests clean
./run-docker-tests start
```

### Test Failures
1. Ensure container is running: `./run-docker-tests status`
2. Check VNC viewer for visual debugging: `./run-docker-tests vnc`
3. Review test logs in `target/surefire-reports/`

### Performance Issues
- Increase container memory: Edit `docker-compose.yml` shm_size
- Scale Grid nodes: Use `docker/docker-compose.selenium-grid.yml`
- Close VNC viewer when not needed

## 🎯 Next Steps

1. **Integrate with CI/CD**: Use docker-compose in your build pipeline
2. **Parallel Testing**: Scale up with Selenium Grid configuration
3. **Custom Browsers**: Extend Dockerfile for specific browser versions
4. **Test Data**: Mount test data volumes for data-driven tests

## 📚 Documentation

- [Docker README](docker/README.md) - Detailed Docker configuration
- [Selenium Grid Setup](docker/docker-compose.selenium-grid.yml) - Advanced configuration
- [Test Examples](src/test/java/actual_test/) - Working test implementations
