import { config } from './config/config.js';
import express from 'express';
import cors from 'cors';
import path from 'path';

var app = express();
const __dirname = path.resolve();

app.use(cors());
app.use('/static', express.static(path.join(__dirname + '/static')));

app.use('/', (req, res) => {
    res.sendFile(path.join(__dirname + '/static/index.html'));
})

app.listen(config.PORT, () => {
    console.log(`Serving on PORT:${config.PORT}`);
})
