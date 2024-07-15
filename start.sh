#!/bin/bash

npx kill-port 8080
npx kill-port 4200

# Step 1: Clean and install the Java project
echo "Building the Java project..."
mvn clean
mvn install -DskipTests

# Step 2: Run the Java application
echo "Running the Java application..."
JAR_FILE="target/techincal-test-0.0.1-SNAPSHOT.jar"
if [ -f "$JAR_FILE" ]; then
    java -jar $JAR_FILE --server.port=8080 &
    JAVA_PID=$!
else
    echo "JAR file not found: $JAR_FILE"
    exit 1
fi

# Step 3: Install npm dependencies for the frontend
echo "Installing npm dependencies..."
cd frontend
npm install

# Step 4: Run the frontend application
echo "Running the frontend application..."
npm run serve -- --port 4200 &
FRONTEND_PID=$!

# Wait for both applications to exit
wait $JAVA_PID
wait $FRONTEND_PID
