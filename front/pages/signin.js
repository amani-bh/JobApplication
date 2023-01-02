import { useRouter } from "next/router"
import { useState } from "react"

export default function SignIn() {
  const router = useRouter()

  const [state, setState] = useState({
    email: "",
    password: ""
  })

  function handleChange(e) {
    const copy = { ...state }
    copy[e.target.name] = e.target.value
    setState(copy)
  }

  async function handleSubmit() {
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/auth/signin`, {
      method: "POST",
      body: JSON.stringify(state),
      headers: {
        "Content-Type": "application/json"
      }
    })
    if (res.ok) {
      const json = await res.json()
      localStorage.setItem("token", json.accessToken)
      fetchProfile()
      router.push("/jobs")
      refreshPage()
    } else {
      alert("Bad credentials")
    }
  }
  async function fetchProfile() {
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/auth/profile`, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
      }
    })
    if (res.ok) {
      const json = await res.json()
      localStorage.setItem("user", JSON.stringify(json))

    } 
  }
  function refreshPage() {
    setTimeout(()=>{
        window.location.reload(false);
    }, 200);
    console.log('page to reload')
}
  return (
      
    

    <>
    {/* Hero Area Start*/}
    <div className="slider-area ">
       <div className="single-slider section-overly slider-height2 d-flex align-items-center" >
         <div className="container">
           <div className="row">
             <div className="col-xl-12">
               <div className="hero-cap text-center">
                 <h2>Sign In</h2>
               </div>
             </div>
           </div>
         </div>
       </div>
     </div>
     {/* Hero Area End */}
     
     <section className="contact-section">
   <div className="container">
       
   
   <div className="row">
       <div className="col-12">
           <h2 className="contact-title">Sign In</h2>
       </div>
       <div className="col-lg-8">
          
               <div className="row">
               
                   <div className="col-sm-6">
                       <div className="form-group">
                           <input className="form-control valid" name="email" id="email" type="email"  placeholder="Email" value={state.email} onChange={handleChange}/>
                       </div>
                   </div>
                   <div className="col-sm-6">
                       <div className="form-group">
                           <input className="form-control valid" name="password" id="password" type="password"  placeholder="Enter your password" value={state.password} onChange={handleChange}/>
                       </div>
                   </div>
                   
                   
               </div>
               <div className="form-group mt-3">
                   <button type="submit" className="button button-contactForm boxed-btn"  onClick={handleSubmit}>Send</button>
               </div>
          
       </div>
   
   </div>
</div>
</section>
     </>
  )
}