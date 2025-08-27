#!/bin/bash

# Run Tests Against Docker Selenium

echo "🧪 Running Selenium Tests with Docker..."

# Check if container is running
if ! [ "$(docker ps -q -f name=selenium-chrome)" ]; then
    echo "❌ Selenium Chrome container is not running"
    echo "💡 Start it with: ./scripts/start-selenium.sh"
    exit 1
fi

# Default test class or use provided argument
TEST_CLASS=${1:-"DockerDemoTest"}

echo "🎯 Running test class: $TEST_CLASS"
echo "🐳 Using Docker Selenium Container"

# Run the tests
mvn test \
    -Dtest="$TEST_CLASS" \
    -Dselenium.remote=true \
    -Djacoco.skip=true

echo ""
echo "📊 Test execution completed"
echo "🖥️  Watch tests: http://localhost:7900 (password: secret)"
echo "🌐 Grid Console: http://localhost:4444"
