pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'saladius/chrifacile'
        DOCKER_TAG = 'latest'
        JAVA_HOME_WINDOWS = 'C:\\Program Files\\Java\\jdk-17'
        JAVA_HOME_LINUX = '/usr/lib/jvm/java-17-openjdk-17.0.13.0.11-3.el9.alma.1.x86_64' // Example for Linux
    }
    stages {
        stage('Set JAVA_HOME') {
            steps {
                script {
                    // Set JAVA_HOME based on the operating system
                    if (isUnix()) {
                        env.JAVA_HOME = JAVA_HOME_LINUX
                    } else {
                        env.JAVA_HOME = JAVA_HOME_WINDOWS
                    }
                    echo "JAVA_HOME is set to ${env.JAVA_HOME}"
                }
            }
        }
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        // Set executable permissions for the mvnw script
                        sh 'chmod +x ./mvnw'
                        // Use shell for Linux/Unix systems
                        sh './mvnw clean package'
                    } else {
                        // Use batch script for Windows systems
                        bat 'mvnw.cmd clean package'
                    }
                }
                script {
                    def jarFile = 'target/chrifacile-0.0.5-SNAPSHOT.jar'
                    if (!fileExists(jarFile)) {
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
    }
}
