import Link from "next/link";
import { useRouter } from "next/router";
import { useState, useEffect } from "react";

export default function Header() {
  const [profile, setProfile] = useState();
  const router = useRouter();

  useEffect(() => {
    fetchProfile();
  }, []);
  async function fetchProfile() {
    const res = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL}/api/auth/profile`,
      {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    );
    if (res.ok) {
      const json = await res.json();
      localStorage.setItem("user", JSON.stringify(json));

      setProfile(json);
    } else {
      router.push("/signin");
    }
  }
  function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    router.push("/");
    refreshPage();
  }
  function refreshPage() {
    setTimeout(() => {
      window.location.reload(false);
    }, 200);
    console.log("page to reload");
  }
  return (
    <header>
      {/* Header Start */}
      <div className="header-area header-transparrent">
        <div className="headder-top header-sticky">
          <div className="container">
            <div className="row align-items-center">
              <div className="col-lg-3 col-md-2">
                {/* Logo */}
                <div className="logo">
                  <a href="index.html">
                    <img src="/assets/img/logo/logo.png" alt="" />
                  </a>
                </div>
              </div>
              <div className="col-lg-9 col-md-9">
                <div className="menu-wrapper">
                  {/* Main-menu */}
                  <div className="main-menu">
                    <nav className="d-none d-lg-block">
                      <ul id="navigation">
                        <li>
                          <Link href="/">Home</Link>
                        </li>
                        <li>
                          <Link href="/jobs">Find a Jobs </Link>
                        </li>
                      </ul>
                    </nav>
                  </div>
                  {/* Header-btn */}
                  <div className="header-btn d-none f-right d-lg-block">
                    {profile != null ? (
                      <>
                        <span>
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="30"
                            height="30"
                            fill="currentColor"
                            className="bi bi-person"
                            viewBox="0 0 16 16"
                          >
                            <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z" />
                          </svg>
                        </span>
                        {profile && profile.username}

                        <button className="btn head-btn2 ml-5" onClick={logout}>
                          Log out
                        </button>
                      </>
                    ) : (
                      <>
                        <Link href="/signup" className="btn head-btn1">
                          Register
                        </Link>
                        <Link href="/signin" className="btn head-btn2">
                          Login <i className="bi bi-person"></i>
                        </Link>
                      </>
                    )}
                  </div>
                </div>
              </div>
              {/* Mobile Menu */}
              <div className="col-12">
                <div className="mobile_menu d-block d-lg-none" />
              </div>
            </div>
          </div>
        </div>
      </div>
      {/* Header End */}
    </header>
  );
}
