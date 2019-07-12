pipeline {
    agent none
    stages {
        stage('Deployment Token Refresh'){
            agent any
            steps {
                sh script: 'docker login -u oauth2accesstoken -p "$(docker run eu.gcr.io/sape-rbs-gcp-poc/util gcloud auth print-access-token)" https://eu.gcr.io'
            }
        }
        stage('Development Deploy') {
            agent {
                docker { image 'eu.gcr.io/sape-rbs-gcp-poc/util' }
            }
            steps {
                sh script: 'kubectl apply -f dev/namespace.yaml', label: 'Configure Namespace'
                sh script: 'kubectl apply -f dev/config.yaml', label: 'Deploy ConfigMap and Secrets'
                sh script: 'kubectl apply -f dev/deployment.yaml', label: 'Execute Deployment'
            }
        }
        stage('QA Deploy') {
            agent {
                docker { image 'eu.gcr.io/sape-rbs-gcp-poc/util' }
            }
            steps {
                sh script: 'kubectl apply -f qa/namespace.yaml', label: 'Configure Namespace'
                sh script: 'kubectl apply -f qa/config.yaml', label: 'Deploy ConfigMap and Secrets'
                sh script: 'kubectl apply -f qa/deployment.yaml', label: 'Execute Deployment'
            }
        }
        stage('Production Deploy'){
            agent {
                docker { image 'eu.gcr.io/sape-rbs-gcp-poc/util' }
            }
            steps {
                sh script: 'kubectl apply -f prod/namespace.yaml', label: 'Configure Namespace'
                sh script: 'kubectl apply -f prod/config.yaml', label: 'Deploy ConfigMap and Secrets'
                sh script: 'kubectl apply -f prod/deployment.yaml', label: 'Execute Deployment'
            }
        }
    }
}