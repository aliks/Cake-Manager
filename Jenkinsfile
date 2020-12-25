pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn -s ~/.m2/settings-default.xml -B -DskipTests clean package'
            }
        }
    }
}