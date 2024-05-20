
<h1 align="center">Exadel CompreFace is a leading free and open-source face recognition system</h1>

<p align="center">
    <a target="_blank" href="https://exadel.com/solutions/compreface/">
  <img src="https://user-images.githubusercontent.com/3736126/147130206-17234c47-8d40-490f-8d93-57014fa6d87e.png" alt="angular-logo" height="250px"/>
 </a>
  <br>
  <i>Exadel CompreFace is a free and open-source face recognition service that can be easily integrated into any system without prior machine learning skills. 
     CompreFace provides REST API for face recognition, face verification, face detection, landmark detection, mask detection, head pose detection, age, and gender recognition and is easily deployed with docker.
     </i>
  <br>
</p>

<p align="center">
  <a href="https://exadel.com/solutions/compreface/"><strong>Official website</strong></a>
  <br>
</p>

<p align="center">
  <a href="#contributing">Contributing</a>
  ·
  <a href="https://github.com/exadel-inc/CompreFace/issues">Submit an Issue</a>
  ·
  <a href="https://exadel.com/news/tag/compreface/">Blog</a>
  ·
  <a href="https://gitter.im/CompreFace/community">Community chat</a>
  <br>
</p>

<p align="center">
  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img src="https://img.shields.io/github/license/exadel-inc/CompreFace" alt="GitHub license" />
  </a>&nbsp;
  <a href="https://github.com/exadel-inc/CompreFace/graphs/contributors">
    <img src="https://img.shields.io/github/contributors/exadel-inc/CompreFace" alt="GitHub contributors" />
  </a>&nbsp;
</p>
<hr>

# Table Of Contents

  * [Overview](#overview)
  * [Screenshots](#screenshots)
  * [Video tutorials](#videos)
  * [News and updates](#news-and-updates)
  * [Features](#features)
  * [Functionalities](#functionalities)
  * [Getting Started with CompreFace](#getting-started-with-compreface)
  * [CompreFace SDKs](#compreface-sdks)
  * [Documentation](/docs)
    * [How to Use CompreFace](/docs/How-to-Use-CompreFace.md)
    * [Face Services and Plugins](/docs/Face-services-and-plugins.md)
    * [Rest API Description](/docs/Rest-API-description.md)
    * [Postman documentation and collection](https://documenter.getpostman.com/view/17578263/UUxzAnde)
    * [Face Recognition Similarity Threshold](/docs/Face-Recognition-Similarity-Threshold.md)
    * [Configuration](/docs/Configuration.md)
    * [Architecture and Scalability](/docs/Architecture-and-scalability.md)
    * [Custom Builds](/docs/Custom-builds.md)
    * [Face data migration](/docs/Face-data-migration.md)
    * [User Roles System](/docs/User-Roles-System.md)
    * [Face Mask Detection Plugin](/docs/Mask-detection-plugin.md)
    * [Kubernetes configuration](https://github.com/exadel-inc/compreface-kubernetes)
    * [Gathering Anonymous Statistics](/docs/Gathering-anonymous-statistics.md)
    * [Installation Options](/docs/Installation-options.md)
  * [Contributing](#contributing)
  * [License info](#license-info)

# Overview

Exadel CompreFace is a free and open-source face recognition GitHub project. 
Essentially, it is a docker-based application that can be used as a standalone server or deployed in the cloud. 
You don’t need prior machine learning skills to set up and use CompreFace.

The system provides REST API for face recognition, face verification, face detection, landmark detection, mask detection, head pose detection, age, and gender recognition. 
The solution also features a role management system that allows you to easily control who has access to your Face Recognition Services.

CompreFace is delivered as a docker-compose config and supports different models that work on CPU and GPU. 
Our solution is based on state-of-the-art methods and libraries like FaceNet and InsightFace.

# Screenshots

<p align="center">
<img src="https://github.com/exadel-inc/CompreFace/assets/3736126/7b86a96f-844b-4e4b-9456-c53f6e45f305" 
alt="compreface-recognition-page" width=390px style="padding: 0px 10px 0px 0px;">
<img src="https://github.com/exadel-inc/CompreFace/assets/3736126/51efb9d0-70cc-4902-bc3f-fd85de004b67" 
alt="compreface-dashboard-page" width="390px" style="padding: 0px 0px 0px 10px;">
</p>

<details>
  <summary> <b>More Screenshots</b> </summary>
  <!-- have to be followed by an empty line! -->

<p align="center">
<img src="https://github.com/exadel-inc/CompreFace/assets/3736126/3ae0ce68-588b-4370-8eaf-32668c96fa63"
alt="compreface-verification-page" width=390px style="padding: 0px 10px 0px 0px;">
<img src="https://github.com/exadel-inc/CompreFace/assets/3736126/9246702d-1c9b-4435-8098-89e0fb616b0d"
alt="compreface-detection-page" width="390px" style="padding: 0px 0px 0px 10px;">
</p>
<p align="center">
<img src="https://github.com/exadel-inc/CompreFace/assets/3736126/3a5787e6-9a85-4852-92dc-a82fe7ef8f7c" 
alt="compreface-services-page" width=390px style="padding: 0px 10px 0px 0px;">
<img src="https://github.com/exadel-inc/CompreFace/assets/3736126/e7fd0258-2643-4cec-809d-988502eb857f" 
alt="compreface-wizzard-page" width="390px" style="padding: 0px 0px 0px 10px;">
</p>

</details>

# Videos

<p align="center">
<a target="_blank" href="https://www.youtube.com/watch?v=LS4sVTnI-gI">
     <img src="https://user-images.githubusercontent.com/3736126/241272669-8609463b-8b22-4ae7-bf21-36761f00734b.jpg" 
        alt="CompreFace Face Detection Demo" width=390px style="padding: 0px 10px 0px 0px;">
</a>
<a target="_blank" href="https://www.youtube.com/watch?v=jkiA3S-LYSk">
     <img src="https://user-images.githubusercontent.com/3736126/242002411-3c06d3f7-c0ac-49f8-ac79-42bd8c431570.png" 
        alt="CompreFace Appery.io Demo" width=390px style="padding: 0px 10px 0px 0px;">
</a>
</p>

<details>
  <summary> <b>More Videos</b> </summary>
  <!-- have to be followed by an empty line! -->

<p align="center">
<a target="_blank" href="https://www.youtube.com/watch?v=cF3P7bTJXY0">
     <img src="https://user-images.githubusercontent.com/3736126/241274256-0dc6d8a0-91d5-42c4-b029-200b72bb169b.jpg" 
        alt="CompreFace .NET SDK Demo" width=390px style="padding: 0px 10px 0px 0px;">
</a>
<a target="_blank" href="https://www.youtube.com/watch?v=9mQULPrTVP4">
     <img src="https://user-images.githubusercontent.com/3736126/241274522-a152221f-e382-416c-9a71-f7694e73cf3e.jpg" 
        alt="CompreFace JavaScript SDK Demo" width=390px style="padding: 0px 10px 0px 0px;">
</a>
</p>

</details>

# News and updates

[Subscribe](https://info.exadel.com/en/compreface-news-and-updates) to CompreFace News and Updates to never miss new features and product improvements.

# Features
The system can accurately identify people even when it has only “seen” their photo once. Technology-wise, CompreFace has several advantages over similar free face recognition solutions. CompreFace:

- Supports both CPU and GPU and is easy to scale up
- Is open source and self-hosted, which gives you additional guarantees for data security
- Can be deployed either in the cloud or on premises
- Can be set up and used without machine learning expertise
- Uses FaceNet and InsightFace libraries, which use state-of-the-art face recognition methods
- Starts quickly with just one docker command

# Functionalities

- Supports many face recognition services:
  - [face detection](/docs/Face-services-and-plugins.md#face-detection)
  - [face recognition](/docs/Face-services-and-plugins.md#face-recognition)
  - [face verification](/docs/Face-services-and-plugins.md#face-verification)
  - [landmark detection plugin](/docs/Face-services-and-plugins.md#face-plugins)
  - [age recognition plugin](/docs/Face-services-and-plugins.md#face-plugins)
  - [gender recognition plugin](/docs/Face-services-and-plugins.md#face-plugins)
  - [face mask detection plugin](/docs/Face-services-and-plugins.md#face-plugins)
  - [head pose plugin](/docs/Face-services-and-plugins.md#face-plugins)
- Use the CompreFace UI panel for convenient user roles and access management

# Getting Started with CompreFace

### Requirements

1. Docker and Docker compose (or Docker Desktop)
2. CompreFace could be run on most modern computers with [x86 processor](https://en.wikipedia.org/wiki/X86) and [AVX support](https://en.wikipedia.org/wiki/Advanced_Vector_Extensions).
   To check AVX support on Linux run `lscpu | grep avx` command

### To get started (Linux, MacOS):

1. Install Docker and Docker Compose
2. Download the archive from our latest release: https://github.com/exadel-inc/CompreFace/releases
3. Unzip the archive
4. Open the terminal in this folder and run this command: `docker-compose up -d`
5. Open the service in your browser: http://localhost:8000/login

### To get started (Windows):

1. Install Docker Desktop
2. Download the archive from our latest release: https://github.com/exadel-inc/CompreFace/releases
3. Unzip the archive
4. Run Docker
5. Open Command prompt (write `cmd` in windows search bar)
6. Open folder where you extracted zip archive (Write `cd path_of_the_folder`, press enter).
7. Run command: `docker-compose up -d`
8. Open http://localhost:8000/login

### Getting started for contributors

Follow this [link](/dev)

# CompreFace SDKs

| SDK        | Repository                                              |
|------------|---------------------------------------------------------|
| JavaScript | https://github.com/exadel-inc/compreface-javascript-sdk |
| Python     | https://github.com/exadel-inc/compreface-python-sdk     |
| .NET       | https://github.com/exadel-inc/compreface-net-sdk        |

# Documentation

More documentation is available [here](/docs)

# Contributing

We want to improve our open-source face recognition solution, so your contributions are welcome and greatly appreciated. 

* Just use CompreFace and [report](https://github.com/exadel-inc/CompreFace/issues) ideas and bugs on GitHub
* Share knowledge and experience via posting guides and articles, or just improve our [documentation](https://github.com/exadel-inc/CompreFace/tree/master/docs)
* Create [SDKs](https://github.com/topics/compreface-sdk) for favorite programming language, we will add it to our documentation
* Integrate CompreFace support to other platforms like [Home Assistant](https://www.home-assistant.io/) or [DreamFactory](https://www.dreamfactory.com/), we will add it to our documentation
* [Contribute](CONTRIBUTING.md) code
* Add [plugin](/docs/Face-services-and-plugins.md#face-plugins) to face services
* And last, but not least, you can just give a star to our free facial recognition system on GitHub

For more information, visit our [contributing](CONTRIBUTING.md) guide, or create a [discussion](https://github.com/exadel-inc/CompreFace/discussions).

# License info 

CompreFace is open-source real-time facial recognition software released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).



Win10 字符集修改为 UTF-8

斵冰且行

于 2020-09-21 14:30:25 发布

阅读量1.2w
 收藏 13

点赞数 7
分类专栏： Windows 使用技巧 文章标签： Windows 字符集 GBK UTF-8
版权

Windows 使用技巧
专栏收录该内容
1 篇文章1 订阅
订阅专栏
Windows 操作系统默认使用 GBK 字符集，这对程序员很不友好。

如今时代变了，Win10 已经悄悄上线了 UTF-8 字符集。只需短短几步，就可轻松将字符集修改为 UTF-8。

依次点击控制面板 -> 时钟和区域 -> 区域，弹出区域对话框。在管理选项卡中找到非 Unicode 程序的语言，点击更改系统区域设置，勾选Beta 版：使用 Unicode UTF-8 提供全球语言支持(U)。

大功告成！！！ 




sudo docker run --gpus all  -d --name=face   -p 3020:3000 --runtime=nvidia  face_20240115:v1.0




#启动无人机镜像命令
sudo docker run --gpus all  -itd --name=drone  -p 9202:9202 --runtime=nvidia  ubuntu_base:v1.1



docker run -itd -p 8848:8848 -p 8888:8888 -p 9999:9999 -p 9998:9998 --name openall openjdk:14 /bin/bash



sudo docker exec -it CONTAINER_ID /bin/bash


 sudo docker exec -it ubuntu_drone_base /bin/bash
 
 
 
 #文件拷贝
 // docker cp 本地路径 容器ID:容器路径
docker cp /usr/local/test.txt 775c7c9ee1e1:/usr/local/tomcat/webapps test.txt
 
 
 
  563  sudo systemctl restart docker
  564  sudo docker run --gpus all  -d --name=face   -p 3020:3000 --runtime=nvidia  face_20240115:v1.0
  565  sudo vim /etc/docker/daemon.json
  566  sudo docker run --gpus all  -d --name=face   -p 3020:3000 --runtime=nvidia  face_20240115:v1.0
  567  curl -fsSL https://nvidia.github.io/libnvidia-container/gpgkey | sudo gpg --dearmor -o /usr/share/keyrings/nvidia-container-toolkit-keyring.gpg   && curl -s -L https://nvidia.github.io/libnvidia-container/stable/deb/nvidia-container-toolkit.list |     sed 's#deb https://#deb [signed-by=/usr/share/keyrings/nvidia-container-toolkit-keyring.gpg] https://#g' |     sudo tee /etc/apt/sources.list.d/nvidia-container-toolkit.list
  568  sed -i -e '/experimental/ s/^#//g' /etc/apt/sources.list.d/nvidia-container-toolkit.list
  569  sudo curl -fsSL https://nvidia.github.io/libnvidia-container/gpgkey | sudo gpg --dearmor -o /usr/share/keyrings/nvidia-container-toolkit-keyring.gpg   && curl -s -L https://nvidia.github.io/libnvidia-container/stable/deb/nvidia-container-toolkit.list |     sed 's#deb https://#deb [signed-by=/usr/share/keyrings/nvidia-container-toolkit-keyring.gpg] https://#g' |     sudo tee /etc/apt/sources.list.d/nvidia-container-toolkit.list
  570  sudo sed -i -e '/experimental/ s/^#//g' /etc/apt/sources.list.d/nvidia-container-toolkit.list
  571  sudo apt-get update
  572  sudo apt-get install -y nvidia-container-toolkit
  573  sudo systemctl restart docker
  574  sudo docker run --gpus all  -d --name=face   -p 3020:3000 --runtime=nvidia  face_20240115:v1.0
  575  docker ps -a
  576  sudo docker ps -a
  577  curl 127.0.0.1:3200
  578  curl 127.0.0.1:3000
  579  netstat
  580  sudo docker ps -a
  581  curl 127.0.0.1:3020
  582  nvidia-smi
  583  ls
  584  tar -xzvf jdk-17_linux-x64_bin.tar.gz
  585  ls
  586  history
  587  sudo docker ps
  588  sudo docker images
  589  sudo docker rmi postgres
  590  sudo docker rmi postgres:12
  591  ls
  592  history
  593  ls
  594  sudo docker load --input 20240314_postgres-db.tar
  595  ls
  596  history
  597  sudo docker images
  598  sudo docker run -d --name=face-db  -p 5432:5432 exadel/compreface-postgres-db:1.1.0
  599  sudo docker images
  600  sudo docker ps
  601  ifconfig
  602  ls
  603  pwd
  604  ls
  605  vim application.yml
  606  ls
  607  mkdir storage
  608  ls
  609  cd storage/
  610  pwd
  611  cd -
  612  vim application.yml
  613  vim ~/.bashrc
  614  vim ~/.bashrc
  615  vim start.sh
  616  chmod +x start.sh
  617  ls
  618  cd jdk-17.0.10/
  619  ls
  620  pwd
  621  cd ../
  622  ls
  623  vim start.sh
  624  vim start.sh
  625  ./start.sh
  626  java --version
  627  java -jar frs-core-app-0.0.1-SNAPSHOT.jar
  628  ls
  629  ./jdk-17.0.10/bin/java -version
  630  ./jdk-17.0.10/bin/java --version
  631  java --version
  632  ./jdk-17.0.10/bin/java -jar frs-core-app-0.0.1-SNAPSHOT.jar
  633  vim start.sh
  634  ./start.sh
  635  vim start.sh
  636  ./start.sh
  637  ./home/houwei/Work/face/jdk-17.0.10/bin/java
  638  cd ./home/houwei/Work/face/jdk-17.0.10/bin/
  639  cd ./home/houwei/Work/face/jdk-17.0.10/
  640  ls
  641  cd jdk-17.0.10/
  642  pwd
  643  vim start.sh
  644  cd ..
  645  vim start.sh
  646  ./start.sh
  647  cd /home/houwei/Work/face/jdk-17.0.10
  648  cd bin
  649  ls
  650  ./java -version
  651  cd ../..
  652  ./start.sh
  653  vim start.sh
  654  ./start.sh
  655  cat start.sh
  656  ./home/houwei/Work/face/jdk-17.0.10/bin/java -jar frs-core-app-0.0.1-SNAPSHOT.jar  --spring.config.location=application.yml
  657  ./jdk-17.0.10/bin/java -jar frs-core-app-0.0.1-SNAPSHOT.jar   --spring.config.location=application.yml
  658  vim application.yml
  659  ./jdk-17.0.10/bin/java -jar frs-core-app-0.0.1-SNAPSHOT.jar   --spring.config.location=application.yml
  660  vim application.yml
  661  ./jdk-17.0.10/bin/java -jar frs-core-app-0.0.1-SNAPSHOT.jar   --spring.config.location=application.yml
  662  ./jdk-17.0.10/bin/java -jar frs-core-app-0.0.1-SNAPSHOT.jar   --spring.config.location=application.yml
  663  vim start.sh
  
  
   sudo docker exec -it ubuntu_drone_base /bin/bash