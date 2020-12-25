pipeline {
    agent any

    stages {
        stage('Build') {
            sh 'mvn -B -DskipTests clean package'
        }
    }
}