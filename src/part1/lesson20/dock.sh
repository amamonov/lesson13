echo "Staring script" &&
git pull https://www.github.com/amamonov/lesson20/ &&
cd ./src/part1/lesson20/ &&
docker build -t java-app:demo . &&
docker run java-app:demo &&
echo "Script finish"