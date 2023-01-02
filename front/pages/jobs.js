import Link from 'next/link';
import Router from 'next/router';
import React, { useEffect, useState } from 'react'
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { useForm } from "react-hook-form";

export default function Jobs() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [jobs, setJobs] = useState()
  const [villes, setVilles] = useState()
  const [user, setUser] = useState()

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
        const response = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/JobOffers`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
          }
        });
        const list = await response.json();
        setJobs(list);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`https://geo.api.gouv.fr/departements/987/communes`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json"
          }
        });
        const list = await response.json();
        setVilles(list);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);



  async function onSubmit(values) {
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/JobOffers/`+user?.idUser, {
            method: "POST",
            body: JSON.stringify(values, null, 2),
            headers: {
              "Content-Type": "application/json",
              "Authorization": "Bearer " + localStorage.getItem("token")
            }
          })
          if (res.ok) {
            alert("Job added success")
            Router.push("/jobs")
          }
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
                  <h2>Get your job</h2>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      {/* Hero Area End */}

            {/* Job List Area Start */}
            <div className="job-listing-area pt-120 pb-120">
        <div className="container">
          <div className="row">
            {/* Left content */}
            <div className="col-xl-3 col-lg-3 col-md-4">
              <div className="row">
                <div className="col-12">
                 
                </div>
              </div>
           
            </div>
            {/* Right content */}
            <div className="col-xl-9 col-lg-9 col-md-8">
              {/* Featured_job_start */}
              <section className="featured-job-area">
                <div className="container">
                  {/* Count of Job list Start */}
                  <div className="row">
                    <div className="col-lg-12">
                      <div className="count-job mb-35">
                        <span>{jobs?.length} Jobs found</span>
                        {/* Select job items start */}
                        <div className="select-job-items">
                          {user?.authorities[0].authority=="ROLE_COMPANY" &&(
                            <Button variant="primary" onClick={handleShow}>
                            Add job offer
                          </Button>
                          )}
                      

                         
                        </div>
                        {/*  Select job items End*/}
                      </div>
                    </div>
                  </div>
                  {/* Count of Job list End */}
                  {/* single-job-content */}
                  {jobs?.map((job) => (
                        <div className="single-job-items mb-30"  key={job.idJob}>
                        <div className="job-items">
                          <div className="company-img">
                            <a href="#"><img src="assets/img/icon/job-list1.png" alt="" /></a>
                          </div>
                          <div className="job-tittle job-tittle2">
                            <Link   href={{ pathname: "/job_details", query: {"id":job.idJob}, }} >
                              <h4>{job.title}</h4>
                            </Link>
                            <ul>
                              <li>{job.employer}</li>
                              <li><i className="fas fa-map-marker-alt" />{job.ville}</li>
                              
                            </ul>
                          </div>

                        </div>
                       

                        <div className="items-link items-link2 f-right">
                          <a href="job_details.html">{job.jobType}</a>
                          <span>{job.creationDate}</span>
                        </div>
                        <div > {job.description.substring(0, 100)}</div>
                      </div>
                  ))}
               
                 
                 
                  
                 
                 
                
                </div>
              </section>
              {/* Featured_job_end */}
            </div>
          </div>
        </div>
      </div>
      {/* Job List Area End */}
      <Modal show={show} onHide={handleClose}>
        <Modal.Header >
          <Modal.Title>Add new job</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <form onSubmit={handleSubmit(onSubmit)}>
        <div className="row">
            <div className="col-sm-6">
              <div className="form-group">
              <label>Title</label>

                <input
                    type="text"
                    className="form-control valid"
                    placeholder="Title"
                    {...register("title", { required: true})}
                    />
                    {errors.title && <span className="text-danger">Title is required</span>}
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
              <label>Name</label>

                <input
                    type="text"
                    className="form-control valid"
                    placeholder="Name"
                    {...register("employer", { required: true})}
                    />
                    {errors.employer && <span className="text-danger">Name is required</span>}
              </div>
            </div>

           

                <div className="col-sm-6">
                        <div className="form-group">
                    <label>Select Type of contract</label>

                          <select className="form-control"  name="jobType" {...register("jobType", { required: true})}>
                 
                            <option >CDI</option>
                            <option >CDD</option>
                            <option >Interim</option>
                            <option >Internship</option>
                          </select>
                    {errors.jobType && <span className="text-danger">Type of contract is required</span>}

                          </div>
                    </div>

                    <div className="col-sm-6">
                   <div className="form-group">
                    <label>Select Location</label>
                   <select className="form-control"  name="ville" {...register("ville", { required: true})}>
                    
                    {villes?.map((option) => (
                      <option value={option.nom} key={option.code}>{option.nom}</option>
                    ))}
                        
          </select>
            {errors.ville && <span className="text-danger">Location is required</span>}

          </div>
          </div>

                    <div className="col-12 ">
                              <div className="form-group">
                    <label>Description</label>

                                  <textarea className="form-control w-100" name="description" cols="900" rows="5"  placeholder=" Description"  {...register("description", { required: true})}></textarea>
                           {errors.description && <span className="text-danger">Description is required</span>}
                             
                              </div>
                              </div>
        </div>
        <Button type="submit">
            Add
          </Button>
          </form>


        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          
        </Modal.Footer>
      </Modal>

    </>
  )
}
