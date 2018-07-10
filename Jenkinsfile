pipeline {
    agent any   
    stages {
    
        stage('Checkout') {
            steps {
                echo "Start checkout project"
                sh 'env'
                step([$class: 'WsCleanup'])
                git url: 'https://github.com/msy53719/cucumber-framework-test.git', branch: 'master'
                echo 'get artifact from pulugins  pipeline.'
            }
        }
        
        stage('Smoking Test') {
            steps {
                sh 'env'
                echo 'execute test'
                sh 'sh ./script/execute_test.sh'
            }
        }
        
          stage('send report') {
            steps {
             mail bcc: '', body: '${SCRIPT, template="groovy-html.template"}', cc: '479979298@qq.com', from: 'tianjiao223@sina.cn', replyTo: '', subject: '测试报告', to: '479979298@qq.com'
            }            
        }
    }
    post {
   
        always {
        
         publishHTML target: [
              allowMissing: false,
              alwaysLinkToLastBuild: false,
              keepAll: true,
              reportDir: './target/cucumber/',
              reportFiles: 'index.html',
              reportName: 'Html Report'
            ]
            echo 'package report'
            sh 'sh ./script/report.sh'
            archiveArtifacts artifacts: 'test-report*.tar.gz', fingerprint: true
        }
        failure {
            echo 'this area is run when failure'
        }
    }

}

