# Dockerfile for Selenium Test Framework
FROM maven:3.9.4-openjdk-17-slim

# Set working directory
WORKDIR /app

# Install necessary packages for browser automation
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Install Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Copy pom.xml first for dependency caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src/ ./src/
COPY *.xml ./

# Set environment variables
ENV MAVEN_OPTS="-Xmx1024m"

# Default command to run tests
CMD ["mvn", "test"]
