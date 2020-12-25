pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn -s ~/.m2/settings-default.xml -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}