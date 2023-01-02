import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react'
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { useForm } from "react-hook-form";
import FormData from 'form-data';
import axios from "axios";
import Link from 'next/link';

export default function JobDetails() {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const router = useRouter();
const data = router.query;
const [job, setJob] = useState()
const [user, setUser] = useState()
const [imageData, setImageData] = useState(null);
const {
  register,
  handleSubmit,
  formState: { errors }
} = useForm();
;

useEffect(() => {
  setUser(JSON.parse(localStorage.getItem("user"))) ;

}, [])
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/JobOffers/`+data.id, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
          }
        });
        const res = await response.json();
        setJob(res);

      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
    
  }, []);


  

  async function onSubmit(values) {
    try {
    await axios.post(`${process.env.NEXT_PUBLIC_API_URL}/CandidateApp/upload`,imageData,{headers: {
      "Content-Type": "multipart/form-data",
      "Authorization": "Bearer " + localStorage.getItem("token")
    }}).then((v) =>{
      apply(values,v.data)
    });
    }

    catch (error) {
      console.log(error);
    }


        
  }

  async function apply(values,id) {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/CandidateApp/`+data.id+'/'+id, {
      method: "Post",
      body: JSON.stringify(values, null, 2),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
      }
    });
    const res = await response.json();
    alert("Application success")
  }



const handleFileChange = ({ target }) => {
  let file =target.files[0];
  const imageData = new FormData();
  imageData.append('file', file);
  setImageData(imageData);
};


  return (
    <div>
    {/* Hero Area Start*/}
    <div className="slider-area ">
      <div className="single-slider section-overly slider-height2 d-flex align-items-center" data-background="assets/img/hero/about.jpg">
        <div className="container">
          <div className="row">
            <div className="col-xl-12">
              <div className="hero-cap text-center">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    {/* Hero Area End */}
    
    {/* job post company Start */}
    <div className="job-post-company pt-120 pb-120">
      <div className="container">
        <div className="row justify-content-between">
          {/* Left Content */}
          <div className="col-xl-7 col-lg-8">
          <div>
     <Link href="/jobs"><h4>  <i className="fas fa-arrow-left" /> Back</h4></Link> 
    </div>
            {/* job single */}
            <div className="single-job-items mb-50">
              <div className="job-items">
                <div className="company-img company-img-details">
                  <a href="#"><img src="assets/img/icon/job-list1.png" alt="" /></a>
                </div>
                <div className="job-tittle">
                  <a href="#">
                    <h4>{job?.title}</h4>
                  </a>
                  <ul>
                    <li>{job?.employer}</li>
                    <li><i className="fas fa-map-marker-alt" />{job?.ville}</li>
                  </ul>
                </div>
              </div>
            </div>
            {/* job single End */}
            <div className="job-post-details">
              <div className="post-details1 mb-50">
                {/* Small Section Tittle */}
                <div className="small-section-tittle">
                  <h4>Job Description</h4>
                </div>
                <p>{job?.description}</p>
              </div>

             
            </div>
          </div>
          {/* Right Content */}
          <div className="col-xl-5 col-lg-5">
            <div className="post-details3  mb-50">
              {/* Small Section Tittle */}
              <div className="small-section-tittle">
                <h4>Job Overview</h4>
              </div>
              <ul>
                <li>Posted date : <span>{job?.creationDate}</span></li>
                <li>Location : <span>{job?.ville}</span></li>
                <li>Job nature : <span>{job?.jobType}</span></li>
                <li>Email : <span>{job?.email}</span></li>
                <li>Views : <span>{job?.vuNumber}</span></li>
              </ul>
              {user?.authorities[0].authority=="ROLE_USER" &&(
                             <div className="apply-btn2">
                             <button  className="btn" onClick={handleShow}>Apply Now</button>
                           </div>
                          )}
             
            </div>
           
          </div>
        </div>
      </div>
    </div>
    {/* job post company End */}

    <Modal show={show} onHide={handleClose}>
        <Modal.Header >
          <Modal.Title>Apply</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <form onSubmit={handleSubmit(onSubmit)} >
        <div className="row">
            <div className="col-sm-6">
              <div className="form-group">
              <label>FirstName</label>

                <input
                    type="text"
                    className="form-control valid"
                    placeholder="FirstName"
                    {...register("firstName", { required: true})}
                    />
                    {errors.firstName && <span className="text-danger">FirstName is required</span>}
              </div>
            </div>

            
            <div className="col-sm-6">
              <div className="form-group">
              <label>LastName</label>

                <input
                    type="text"
                    className="form-control valid"
                    placeholder="LastName"
                    {...register("lastName", { required: true})}
                    />
                    {errors.lastName && <span className="text-danger">LastName is required</span>}
              </div>
            </div>

            <div className="col-sm-6">
              <div className="form-group">
              <label>Email</label>

              <input
              type="text"
              className="form-control valid"

              placeholder="Email"
              {...register("email", { required: true, pattern: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i })}
              />
              {errors.email && <span className="text-danger">Invalid email</span>}
              </div>
            </div>


           

                <div className="col-sm-6">
                        <div className="form-group">
                    <label>Select Type of contract</label>

                    <input
                   className="form-control valid"
                    type="file"
                    // {...register("cv", {
                    //   required: "Picture is Required",
                    // })}
                    onChange={handleFileChange}
                    accept="application/pdf"
                  />
                    {errors.cv && <span className="text-danger">CV is required</span>}

                          </div>
                    </div>
 
        </div>
        <Button type="submit">
            Apply
          </Button>
          </form>

        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          
        </Modal.Footer>
      </Modal>
  </div>



  )
}
