pipeline {
    agent none
    stages {
        stage('Test & Install') {
            agent {
                docker { image 'maven:latest' }
            }
            steps {
                sh script: 'mvn clean test', label: 'Test'
                sh script: 'mvn install -DskipTests', label: 'Install'
            }
        }
        stage('Build') {
            agent any
            steps {
                sh script: 'echo $(git rev-parse --short HEAD) > version'
                sh script: 'docker build -t eu.gcr.io/sape-rbs-gcp-poc/rbs-app-one:$(cat version) -t eu.gcr.io/sape-rbs-gcp-poc/rbs-app-one:latest .', label: 'Creating  Image'
            }
        }
        stage('Deployment Token Refresh'){
            agent any
            steps {
                sh script: 'docker login -u oauth2accesstoken -p "$(docker run eu.gcr.io/sape-rbs-gcp-poc/util gcloud auth print-access-token)" https://eu.gcr.io'
            }
        }
        stage('Publish'){
            agent any
            steps {
                sh script: 'docker push eu.gcr.io/sape-rbs-gcp-poc/rbs-app-one:$(cat version)', label: 'Pushing version into registry'
                sh script: 'docker push eu.gcr.io/sape-rbs-gcp-poc/rbs-app-one:latest', label: 'Pushing latest into registry'
                sh script: 'docker rmi -f eu.gcr.io/sape-rbs-gcp-poc/rbs-app-one:latest eu.gcr.io/sape-rbs-gcp-poc/rbs-app-one:$(cat version)', label: 'Image clean up'
            }
        }
        stage('Invoke Infra'){
            agent any
            steps {
                build job: 'rbs-app-one-infra', wait: true, propagate: true, parameters: [string(name: 'version', value: '${cat version}')]
            }
        }
    }
}