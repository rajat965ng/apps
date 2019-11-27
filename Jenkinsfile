pipeline {
    agent none
    stages {
        stage('Development Deploy') {
            withKubeConfig([credentialsId: 'jenkins', serverUrl: 'https://10.0.0.4:6443']){
                echo "${params.version} the version"
                sh script: 'kubectl get nodes', label: 'get nodes'
                sh script: 'sed -i  s/tag_version/`openssl rand -hex 3`/g dev/deployment.yaml'
                sh script: 'kubectl apply -f dev/namespace.yaml', label: 'Configure Namespace'
                sh script: 'kubectl apply -f dev/config.yaml', label: 'Deploy ConfigMap and Secrets'
                sh script: 'kubectl apply -f dev/deployment.yaml', label: 'Execute Deployment'
            }
        }
    }
}