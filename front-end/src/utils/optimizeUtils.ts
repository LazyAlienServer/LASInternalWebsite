/*
* 防抖函数
*
* 应用场景:
* 防抖的主要应用场景是优化搜索框的输入，
* 用户在不断输入值时，
* 用防抖来节约请求资源，
* 当用户最后一次输入完，再发送请求
* */

function debounce(fn:Function, delay = 500) {
    //@ts-ignore
    let timer = null;
    // 这里返回的函数是每次用户实际调用的防抖函数
    //@ts-ignore
    return function(...args) {
        // 如果已经设定过定时器了就清空上一次的定时器
        //@ts-ignore
        if(timer) {
            clearTimeout(timer);
        }
        // 开始一个新的定时器，延迟执行用户传入的方法，这里必须是箭头函数，要让this指向fn的调用者
        timer = setTimeout(() => {
            //@ts-ignore
            fn.apply(this, args);
        }, delay)
    }
}

/*
* 节流函数
*
* 应用场景:
* 节流的主要应用场景是优化滚动事件，
* 当用户滚动页面时，
* 会频繁触发滚动事件，
* 使用节流可以控制滚动事件的触发频率，
* 避免过多的计算和渲染操作，
* 提高页面的性能和流畅度。
* */
function throttle(fn:Function, delay = 500) {
    let prev = Date.now();// 上一次时间
    //@ts-ignore
    return function(...args) {
        let now = Date.now();//当前时间
        // 时间间隔到了就执行函数
        if (now - prev >= delay) {
            //@ts-ignore
            fn.apply(this, args);
            prev = Date.now();
        }
    };
}

export {debounce, throttle}