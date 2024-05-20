# CompreFace documentation

## Links

* [How to Use CompreFace](How-to-Use-CompreFace.md)
* [Face Services and Plugins](Face-services-and-plugins.md)
* [Rest API Description](Rest-API-description.md)
* [Face Recognition Similarity Threshold](Face-Recognition-Similarity-Threshold.md)
* [Configuration](Configuration.md)
* [Architecture and Scalability](Architecture-and-scalability.md)
* [Custom Builds](Custom-builds.md)
* [Face data migration](Face-data-migration.md)
* [User Roles System](User-Roles-System.md)
* [Face Mask Detection Plugin](Mask-detection-plugin.md)
* [Gathering Anonymous Statistics](Gathering-anonymous-statistics.md)
* [Installation Options](/docs/Installation-options.md)


sudo docker run --gpus all  -d --name=face   -p 3020:3000 --runtime=nvidia  face_20240115:v1.0
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