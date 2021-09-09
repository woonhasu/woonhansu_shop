const canvas = document.querySelector('canvas');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

const c = canvas.getContext('2d');

// for (var i = 0; i < 100; i++) {
//     var x=Math.random() * window.innerWidth;
//     var y=Math.random() * window.innerHeight;
//     c.fillStyle='rgba(255,0,0,0.5)';
//     c.fillRect(x,y,50,50)
//     var x=Math.random() * window.innerWidth;
//     var y=Math.random() * window.innerHeight;
//     c.fillStyle ='rgba(0,0,255,0.5)';
//     c.fillRect(x,y,50,50)
//     var x=Math.random() * window.innerWidth;
//     var y=Math.random() * window.innerHeight;
//     c.fillStyle ='rgba(0,255,0,0.5)';
//     c.fillRect(x,y,50,50)
// }
// console.log(canvas);

// // Line

// c.beginPath();
// c.moveTo(50,300);
// c.lineTo(300,100);
// c.lineTo(400,250);
// c.strokeStyle="#fa34a3";
// c.stroke();

// // Arc / Circle
// c.beginPath();
// c.arc(300,100,30, 0, Math.PI*2, false);
// c.strokeStyle='blue';
// c.stroke();

// for (var i = 0; i < 100; i++) {
//     var x=Math.random() * window.innerWidth;
//     var y=Math.random() * window.innerHeight;
//     c.beginPath();
//     c.arc(x,y,30, 0, Math.PI*2, false);
//     c.strokeStyle='blue';
//     c.stroke();   
// }

// var x = Math.random() * innerWidth;
// var y = Math.random() * innerHeight;
// var dy = (Math.random() - 0.5) * 8;
// var dx = (Math.random() - 0.5) * 8;
// var radius = 30;
const mouse = {
    x: undefined, y: undefined
}

window.addEventListener('mousemove', (event) => {
    mouse.x = event.x;
    mouse.y = event.y;
})


window.addEventListener('resize', () => {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    init ()
})
const maxRadius = 40;
const minRadius = 2;

const colorArray = [
    '#00A69C',
    '#FFFFE7',
    '#E8E1FF',
    '#C69DDF',
    '#EB68A7'
];

function Circle(x, y, dx, dy, radius) {
    //this 를 쓰는 이유?
    //this = who = 누가 실행했냐/ 호출한 놈 / bind(); ()안에 있는 애를 this라고 부르겠다
    // * 아마도 parent context 에서 사용할 애들이 어떤건지 bind하는 행위?\
    // 예 = 이 context 안에서 사용할 minRadius 는 const minRadius라고 호출하는 행위?
    // parent context 밖에 있는 변수를 bind하는 것*
    this.x = x;
    this.y = y;
    this.dx = dx;
    this.dy = dy;
    this.radius = radius;
    this.minRadius = minRadius;
    //밖에 있는 colorArray를 가져올 때 random하게 가져오는 커맨드 (이건 더 공부 필요)
    this.color = colorArray[Math.floor(Math.random() * colorArray.length)];
    //2D 서클 그리는 코드
    this.draw = function () {
        c.beginPath();
        //Math.PI * 2 = 아크를 원으로 만드는 공식
        c.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
        // c.strokeStyle= 'blue'
        // c.stroke();
        c.fillStyle = this.color;
        c.fill();
    }
    //원들이 어떻게 canvas 내에서 interactive 하는지 '움직임과. 캐버스 끝을 밖았을 때 팅기는 애니메이션'
    this.update = () => {
        if (this.x + this.radius > innerWidth || this.x - this.radius < 0) {
            this.dx = -this.dx;
        }
    
        if (this.y + this.radius > innerHeight || this.y - this.radius < 0) {
            this.dy = -this.dy;
        }

        this.x += this.dx;
        this.y += this.dy;

        //mouse interactivity
        if (mouse.x - this.x < 50 && mouse.x - this.x > -50 && mouse.y - this.y < 50 && mouse.y - this.y > -50
            ) {
            if (this.radius < maxRadius) {
                this.radius += 1;
            }
           
        } else if (this.radius > this.minRadius) {
            this.radius -= 1;
        } 
        
        this.draw();
       
    }
};
console.log(Circle);


//circleArray Method

//여기선 const 말고 var이나 let을 써야 함 = 내부 value나 string이 후에 바뀔 수 있게
//밑에 코드 circleArray.push() 내부에 새로운 원들을 추가하는 방식이기 때문에 변수 내부 스트링 추가인듯?
let circleArray = [];

//function에 이름을 정할때는 () => {} 안되는 듯?
function init() {
    
    circleArray = [];

    for (var i = 0; i < 600; i++) {
        const radius = Math.random() * 3 + 1;
        const x = Math.random() * (innerWidth - radius * 2) + radius;
        const y = Math.random() * (innerHeight - radius * 2) + radius;
        const dy = (Math.random() - 0.5);
        const dx = (Math.random() - 0.5);
        //서클 추가
        circleArray.push(new Circle(x, y, dx, dy, radius));
    };
};


init();

console.log(circleArray);
function animate() {
    requestAnimationFrame(animate);
    c.clearRect(0, 0, innerWidth, innerHeight);
    
    for (var i = 0; i < circleArray.length; i++) {
        circleArray[i].update();
    }
};



animate();
//Animate In


//Animate Out
