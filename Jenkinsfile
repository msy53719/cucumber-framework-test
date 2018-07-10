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
            emailext attachLog: true, body: '''<!DOCTYPE html>  
<html>  
<head>  
<meta charset="UTF-8">  
<title>${ENV, var="JOB_NAME"}-第${BUILD_NUMBER}次构建日志</title>  
</head>  

<body leftmargin="8" marginwidth="0" topmargin="8" marginheight="4"  
    offset="0">  
    <div>
    <table width="95%" cellpadding="0" cellspacing="0" 
        style="font-size: 11pt; font-family: Tahoma, Arial, Helvetica, sans-serif"> 

        <tr>
            <th align="center" colspan="2"><br />
                <h2>构建信息</h2> 
            </th>
        </tr>
        <tr>  
            <td>  
                <ul>  
                    <li>项目名称 ： ${PROJECT_NAME}</li><br />  
                    <li>详细测试报告 ： <a href="${PROJECT_URL}测试报告">${PROJECT_URL}测试报告</a></li><br />
                    <li>触发原因： ${CAUSE}</li><br />                    
                    <li>项目  Url ： <a href="${PROJECT_URL}">${PROJECT_URL}</a></li><br />
                </ul>  
            </td> 
        </tr>  
    </table> 
    </div>

  </body>  
</html>''', compressLog: true, mimeType: 'html(text/html)', subject: '测试报告', to: '479979298@qq.com'
            
            mail bcc: '', body: '<body><a href></body>', cc: '479979298@qq.com', from: 'tianjiao223@sina.cn', replyTo: '', subject: '测试报告', to: '479979298@qq.com'
            
        }
        failure {
            echo 'this area is run when failure'
        }
    }

}

