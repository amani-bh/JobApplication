import { useRouter } from "next/router";
import { useState } from "react";
import { useForm } from "react-hook-form";

export default function SignUp() {
  const router = useRouter();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();


  async function onSubmit(values) {
    values.roles = [values.roles];
    const res = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL}/api/auth/signup`,
      {
        method: "POST",
        body: JSON.stringify(values, null, 2),
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    if (res.ok) {
      alert("User registered success");
      router.push("/signin");
    }
  }
  return (
    <>
      {/* Hero Area Start*/}
      <div className="slider-area ">
        <div className="single-slider section-overly slider-height2 d-flex align-items-center">
          <div className="container">
            <div className="row">
              <div className="col-xl-12">
                <div className="hero-cap text-center">
                  <h2>Sign Up</h2>
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
              <h2 className="contact-title">Sign Up</h2>
            </div>
            <div className="col-lg-8">
              <div className="row">
                <form onSubmit={handleSubmit(onSubmit)}>
                  <div className="row">
                    <div className="col-sm-6">
                      <div className="form-group">
                        <label>First Name</label>

                        <input
                          type="text"
                          className="form-control valid"
                          placeholder="First Name"
                          {...register("firstName", { required: true })}
                        />
                        {errors.firstName && (
                          <span className="text-danger">
                            First Name is required
                          </span>
                        )}
                      </div>
                    </div>

                    <div className="col-sm-6">
                      <div className="form-group">
                        <label>Last Name</label>

                        <input
                          type="text"
                          className="form-control valid"
                          placeholder="Last Name"
                          {...register("lastName", { required: true })}
                        />
                        {errors.lastName && (
                          <span className="text-danger">
                            Last Name is required
                          </span>
                        )}
                      </div>
                    </div>

                    <div className="col-sm-6">
                      <div className="form-group">
                        <label>Email</label>

                        <input
                          type="text"
                          className="form-control valid"
                          placeholder="Email"
                          {...register("email", {
                            required: true,
                            pattern:
                              /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i,
                          })}
                        />
                        {errors.email && (
                          <span className="text-danger">Invalid email</span>
                        )}
                      </div>
                    </div>

                    <div className="col-sm-6">
                      <div className="form-group">
                        <label>Address</label>

                        <input
                          type="text"
                          className="form-control valid"
                          placeholder="Address"
                          {...register("address", { required: true })}
                        />
                        {errors.address && (
                          <span className="text-danger">
                            Address is required
                          </span>
                        )}
                      </div>
                    </div>

                    <div className="col-sm-6">
                      <div className="form-group">
                        <label>Country</label>

                        <input
                          type="text"
                          className="form-control valid"
                          placeholder="Country"
                          {...register("country", { required: true })}
                        />
                        {errors.country && (
                          <span className="text-danger">
                            Country is required
                          </span>
                        )}
                      </div>
                    </div>

                    <div className="col-sm-6">
                      <div className="form-group">
                        <label>Phone</label>

                        <input
                          type="text"
                          className="form-control valid"
                          placeholder="Phone"
                          {...register("phone", { required: true })}
                        />
                        {errors.phone && (
                          <span className="text-danger">Phone is required</span>
                        )}
                      </div>
                    </div>

                    <div className="col-sm-6">
                      <div className="form-group">
                        <label>Select Role</label>

                        <select
                          className="form-control"
                          name="roles"
                          {...register("roles", { required: true })}
                        >
                          <option value="user">Candidate</option>
                          <option value="comp">Recruiter</option>
                        </select>
                        {errors.roles && (
                          <span className="text-danger">Role required</span>
                        )}
                      </div>
                    </div>

                    <div className="col-sm-6">
                      <div className="form-group">
                        <label>Password</label>

                        <input
                          type="password"
                          className="form-control valid"
                          {...register("password", { required: true })}
                        />
                        {errors.password && (
                          <span className="text-danger">
                            Password is required
                          </span>
                        )}
                      </div>
                    </div>
                  </div>
                  <button
                    button
                    type="submit"
                    className="button button-contactForm boxed-btn"
                  >
                    Register
                  </button>
                </form>

              
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}
