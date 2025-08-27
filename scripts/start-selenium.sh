#!/bin/bash

# Start Selenium Chrome Container Script

echo "🚀 Starting Selenium Chrome Container..."

# Check if container is already running
if [ "$(docker ps -q -f name=selenium-chrome)" ]; then
    echo "✅ Selenium Chrome container is already running"
    echo "🌐 Grid Console: http://localhost:4444"
    echo "🖥️  VNC Viewer: http://localhost:7900 (password: secret)"
    exit 0
fi

# Start the container
docker run -d \
    --name selenium-chrome \
    -p 4444:4444 \
    -p 7900:7900 \
    --shm-size=2g \
    --platform=linux/amd64 \
    selenium/standalone-chrome

# Wait for container to be ready
echo "⏳ Waiting for Selenium Grid to be ready..."
for i in {1..30}; do
    if curl -s http://localhost:4444/wd/hub/status > /dev/null 2>&1; then
        echo "✅ Selenium Grid is ready!"
        echo ""
        echo "🌐 Grid Console: http://localhost:4444"
        echo "🖥️  VNC Viewer: http://localhost:7900 (password: secret)"
        echo ""
        echo "🧪 Run tests with: ./scripts/run-tests.sh"
        break
    fi
    echo "⏳ Waiting... ($i/30)"
    sleep 2
done
