const express = require('express');
const app=express();
const server=require('http').createServer(app);
const io=require('socket.io')(server);

app.engine('html', require('ejs').renderFile);
app.set('view engine', 'html');
app.use(express.static(__dirname + "/"))

app.get("/client", (req, res) => {
    res.render("client",{})
})

// socket
io.on('connection',function(socket){
    const req = socket.request;
    const ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;
    console.log('새로운 클라이언트 접속', ip, socket.id, req.ip);

    socket.on("start", (data) => {
        console.log(data)
    })
    socket.on("end", (data) => {
        console.log(data)
    })
    socket.on("changeCCTV", (data) => {
        console.log(data)
    })
    socket.on("test", ()=>{
        console.log("test");

        // 1. child-process모듈의 spawn 취득
        const spawn = require('child_process').spawn;
        // 2. spawn을 통해 "python 파이썬파일.py" 명령어 실행
        const result = spawn('python3', ['./pytorch/test.py', '1', "[수도권제1순환선] 성남"]);
        // 3. stdout의 'data'이벤트리스너로 실행결과를 받는다.
        result.stdout.on('data', function(data) { console.log(data.toString()); });
        // 4. 에러 발생 시, stderr의 'data'이벤트리스너로 실행결과를 받는다.
        result.stderr.on('data', function(data) { console.log(data.toString()); });
    })
});

server.listen(3000,()=>{
    console.log('Socket IO server listening on port ');
});