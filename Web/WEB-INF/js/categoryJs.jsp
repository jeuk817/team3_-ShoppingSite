<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

const btn = document.getElementById("btn")
const categoryContent = document.getElementById("categoryContent")


function makeElement(rootEl, className, content){
    const div = document.createElement("div")
    div.className = className
    
    const p = document.createElement("p")
    p.innerHTML=content

    div.appendChild(p)

    rootEl.appendChild(div)
}

btn.addEventListener('click', e => {
    const div = document.createElement("div")
    div.className ="item"

    const img = document.createElement("img")
    img.src = "/team3_ShoppingSite/images/19309420_1598777941_image1_M.jpg"
    
    div.appendChild(img)
    makeElement(div, "store-title", "세미브랜드")
    makeElement(div, "product-description", "세련끝판왕")
    makeElement(div, "hit-number", "1111")
    categoryContent.appendChild(div)

})
</script>
