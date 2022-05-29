job('ejemplo-job-DSL-GIT') {
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main') { node -> // is hudson.plugins.git.GitSCM
      node / gitConfigName('nicolasaceituno')
      node / gitConfigEmail('nicolas.aceituno@gmail.com')
      }
    }
  parameters {
    stringParam('nombre', defaultValue = 'Nicolas', description = 'Parametro de cadena para job booleano')
    choiceParam('planeta', ['Mercurio','Venus','Tierra','Marte','Jupiter','Saturno','Urano','Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
    cron('H/7 * * * *')
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
    mailer('nicolas.aceituno@gmail.com', true, true)
  }
}
