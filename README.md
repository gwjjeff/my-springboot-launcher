## modulized playground of springboot with IntelliJ IDEA

### 建立 maven module 的流程

#### step 1. 
在 playground2 工程文件夹中新建 module，设置 parent 为 launcher-deps
#### step 2. 
修改pom.xml，添加基本依赖

    <dependencies>
        <dependency>
            <groupId>com.gwjjeff</groupId>
            <artifactId>launcher-template</artifactId>
        </dependency>
    </dependencies>

#### step 3. 
创建 package：com.gwjjeff.launchers.*UNIQUENAME*

    package com.gwjjeff.launchers.*UNIQUENAME*;
    
    import lombok.extern.slf4j.Slf4j;
    import com.gwjjeff.base.BaseDemoLauncher;
    
    @Slf4j
    @Component("*UNIQUENAME*_launcher")
    public class Launcher extends BaseDemoLauncher {
    
        @Override
        public void doRun(ApplicationArguments args) throws Exception {
            log.info("=============  *UNIQUENAME* LAUNCHER  ===============");
        }
    }

#### step 4. 
创建classpath:application.yml

    ---
    r:
      launchers:
        - *UNIQUENAME*_launcher
    

#### step 5. 
按照springboot的约定进行开发和配置
