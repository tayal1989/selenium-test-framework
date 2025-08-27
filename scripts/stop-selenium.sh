#!/bin/bash

# Stop Selenium Chrome Container Script

echo "🛑 Stopping Selenium Chrome Container..."

# Stop and remove container
docker stop selenium-chrome 2>/dev/null || true
docker rm selenium-chrome 2>/dev/null || true

echo "✅ Selenium Chrome container stopped and removed"
echo "💡 To start again: ./scripts/start-selenium.sh"
