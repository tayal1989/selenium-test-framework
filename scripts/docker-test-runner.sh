#!/bin/bash

# Docker Test Runner Script for Selenium Framework

echo "🚀 Starting Selenium Test Execution with Docker"

# Function to check if container is running
check_container() {
    if [ "$(docker ps -q -f name=selenium-chrome)" ]; then
        echo "✅ Selenium Chrome container is running"
        return 0
    else
        echo "❌ Selenium Chrome container is not running"
        return 1
    fi
}

# Function to start Selenium container
start_selenium() {
    echo "🐳 Starting Selenium Chrome container..."
    docker run -d -p 4444:4444 -p 7900:7900 --shm-size=2g --name selenium-chrome selenium/standalone-chrome
    
    # Wait for container to be ready
    echo "⏳ Waiting for Selenium Grid to be ready..."
    for i in {1..30}; do
        if curl -s http://localhost:4444/wd/hub/status > /dev/null 2>&1; then
            echo "✅ Selenium Grid is ready!"
            break
        fi
        echo "⏳ Waiting... ($i/30)"
        sleep 2
    done
}

# Function to stop Selenium container
stop_selenium() {
    echo "🛑 Stopping Selenium Chrome container..."
    docker stop selenium-chrome 2>/dev/null || true
    docker rm selenium-chrome 2>/dev/null || true
}

# Function to run tests
run_tests() {
    echo "🧪 Running Selenium Tests..."
    mvn test -Dselenium.remote=true "$@"
}

# Main script logic
case "$1" in
    "start")
        if ! check_container; then
            start_selenium
        fi
        ;;
    "stop")
        stop_selenium
        ;;
    "restart")
        stop_selenium
        start_selenium
        ;;
    "test")
        if ! check_container; then
            start_selenium
        fi
        shift # Remove 'test' from arguments
        run_tests "$@"
        ;;
    "vnc")
        echo "🖥️  Opening VNC Viewer..."
        open http://localhost:7900
        echo "VNC Password: secret"
        ;;
    "grid")
        echo "🌐 Opening Selenium Grid Console..."
        open http://localhost:4444
        ;;
    "logs")
        echo "📋 Showing container logs..."
        docker logs selenium-chrome
        ;;
    *)
        echo "Usage: $0 {start|stop|restart|test|vnc|grid|logs}"
        echo ""
        echo "Commands:"
        echo "  start    - Start Selenium Chrome container"
        echo "  stop     - Stop Selenium Chrome container"
        echo "  restart  - Restart Selenium Chrome container"
        echo "  test     - Start container (if needed) and run tests"
        echo "  vnc      - Open VNC viewer in browser"
        echo "  grid     - Open Selenium Grid console"
        echo "  logs     - Show container logs"
        echo ""
        echo "Examples:"
        echo "  $0 test                           # Run all tests"
        echo "  $0 test -Dtest=GoogleSearchTest   # Run specific test"
        echo "  $0 vnc                           # Watch tests run in browser"
        exit 1
        ;;
esac
