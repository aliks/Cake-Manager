pipeline {
    environment {

        snapshot = 'cake-manager-0.0.1-SNAPSHOT'
        dockerImage = ''
    }
    agent any

    stages {
        stage('Check') {
            steps {
                sh 'mvn -v'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -s ~/.m2/settings-default.xml -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -s ~/.m2/settings-default.xml test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Environment') {
            steps {
                echo sh(script: 'env|sort', returnStdout: true)
            }
        }

        stage('Build Image') {
            steps {
                script {
                    dockerImage = docker.build "cake-app:${env.BUILD_NUMBER}"
                }
            }
        } 
    }
}