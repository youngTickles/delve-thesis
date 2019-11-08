import React,  {Component} from 'react';
import { NavLink as Link} from 'react-router-dom';

import { Container,Card, NavLink, CardImg, CardText, CardBody,
  CardTitle, Button} from 'reactstrap';

import './AppUpcoming.css';

import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import see from '../../images/see.png'
import noPoster from '../../images/imageUnavailable.png';



const responsive = {
  superLargeDesktop: {
    // the naming can be any, depends on you.
    breakpoint: { max: 4000, min: 3000 },
    items: 5,
  },
  desktop: {
    breakpoint: { max: 3000, min: 1024 },
    items: 3,
  },
  tablet: {
    breakpoint: { max: 1024, min: 464 },
    items: 2,
  },
  mobile: {
    breakpoint: { max: 464, min: 0 },
    items: 1,
  },
};

class AppUpcoming extends Component{
  constructor(props){
    super(props);
    this.state = {
      isLoading: true,
      movies: []
    }
    this._isMounted = false;
  }

  componentDidMount(){
    this._isMounted  = true;
    this._isMounted && this.getInformation();
  }

  componentWillUnmount(){
    this._isMounted = false;
  }


  async getInformation(){

   
    const response = await fetch('api/upcoming');
    const body = await response.json();
    this._isMounted && this.setState({
      movies: body,
      isLoading: false
    });
  }

 

  

  render() {
    const { movies, isLoading} = this.state;

    if(isLoading){
      return(
        <div className="loader"></div>
      )
    }
    const columns = movies.map((movie, index) => {

        let movieName = movie.title;
        if(movie.title.length>27){
           movie.title =  movie.title.substring(0,24) + "..." 
        }
        let poster = `http://image.tmdb.org/t/p/original${movie.poster_path}`;
        if(!movie.hasOwnProperty("poster_path")){
          poster = noPoster;
        }

      return(

        <div style={{paddingTop:"25px"}} key={index}>
        <Card style={{maxWidth:"185px", borderColor:" #1c1b1b"}}>
          <CardImg  
            src={poster} alt="Card image cap" style={{border:"4px solid black", height:"278px", width:"185px",maxHeight:"278px", maxWidth:"185px",}} />
            <CardBody className="paddingCardbody">
                <CardTitle className="paddingCardbody" style={{color:"#fec106", textTransform:"capitalize", fontSize:"13px"}} title={movieName}>{movie.title}</CardTitle>
                <CardText className="paddingCardbody" style={{color:"#FFFFFF", textTransform:"capitalize",  fontSize:"12px"}} title={movie.director}>{movie.director}</CardText>
                <CardText  style={{color:"#FFFFFF", textTransform:"capitalize",  fontSize:"12px"}}>{movie.release_date}</CardText>
                  <NavLink tag={Link} exact to="/details" style={{ display:"inline-block", height:"100%", fontSize:"0", margin:"0", padding:"0"}}>
                    <div style={{ display:"inline-block"}}>
                      <Button color="warning" size="sm"><span> <img max-width="15px" max-height="15px" style={{paddingBottom:"2px", paddingRight:"2px"}} src={see} alt=""></img></span>See More</Button>{' '}
                    </div>
                  </NavLink>            
                </CardBody>
          </Card>
      </div>
      );
    });
    return (
          
      <div className="containerDiv">
        <Container style={{paddingBottom: "10px"}}>
          <div className="borderUpcoming"> 
            <div>
              <p className="UpcomingText" style={{paddingRight:"5px"}}>Upcoming</p>
            </div> 
          </div>

        <div className="backgroundUpcoming">
          <div className="imagesUpcoming" > 
           <Carousel arrows={false} infinite={true} responsive={responsive}  autoPlay={this.props.deviceType !== "mobile" ? true : false} autoPlaySpeed={3000}>
            {columns}
            </Carousel>;
          </div>
        </div>
        </Container>
      </div>

    );
  }

}
export default AppUpcoming;