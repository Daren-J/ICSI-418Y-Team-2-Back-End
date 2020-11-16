const server = require("http").createServer();
const io = require("socket.io")(server, {
  transports: ["websocket", "polling"],
});
io.on("connection", (client) => {
  client.on("send", (payload) => {
    io.emit("message", {
      user: payload.user,
      date: new Date().toISOString(),
      text: payload.message,
    });
  });

  client.on("send dm", (payload) => {
    io.emit("dm message", {
      sender: payload.sender,
      date: new Date().toISOString(),
      text: payload.message,
      receiver: payload.receiver,
    });
  });
});
server.listen(3001, () => {
  console.log("Listening on port 3001");
});
