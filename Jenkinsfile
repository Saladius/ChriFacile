pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'saladius/chrifacile'
        DOCKER_TAG = 'latest'
        // Adjust the path to match your Java installation
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package'
                script {
                    if (!fileExists('target/chrifacile-0.0.1-SNAPSHOT.jar')) {
                        error("JAR file not found! Build failed.")
                    }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Ensure the build context is specified (usually '.' for the current directory)
                    docker.build("$DOCKER_IMAGE:$DOCKER_TAG", "--no-cache .")
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image("$DOCKER_IMAGE:$DOCKER_TAG").push()
                    }
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sh 'docker-compose down || true' // Stops any existing containers
                    sh 'docker-compose up -d' // Brings up the new containers
                }
            }
        }
    }
    post {
        always {
            script {
                sh 'docker-compose down' // Clean up containers after pipeline finishes
            }
        }
    }
}