import { Button, Container, InputAdornment, InputLabel, MenuItem, Modal, Paper, Select, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from '@mui/material'
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import API_URL from "./api.js"
import { useNavigate } from 'react-router-dom';

const style = {
    position: "absolute",
    bottom: "-20%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 600,
    bgcolor: "background.paper",
    boxShadow: 24,
    p: 4,
    borderRadius: 5,

};

const TradeDetails = () => {

    const [tradeDetails, setTradeDetails] = useState([])

    // States for Trade Details
    const [tradeDataTime, setTradeDataTime] = useState("")
    const [stockName, setStockName] = useState("")
    const [listingPrice, setListingPrice] = useState(0)
    const [quantity, setQuantity] = useState(0)
    const [type, setType] = useState("")
    const [pricePerUnit, setPricePerUnit] = useState(0)


    // States for Update Modal Open and Track Id for Updating Trade
    const [isUpdateModal, setIsUpdateModal] = useState(0);
    const [tradeId, setTradeId] = useState(0)




    // Function for fetching all trades
    const getAllTrades = async () => {

        try {
            const { data } = await axios.get(`${API_URL}/api/all-trades`);
            console.log(data);
            setTradeDetails(data)
        } catch (error) {
            console.log(error);
        }


    }

    // Useeffect hook is fetch the all trades even before pade loading
    useEffect(() => {
        getAllTrades()

    }, [])

    // States for Opening and Closing Modal
    const [createTradeModalOpen, setCreateTradeModalOpen] = useState(false);


    // Function for open the Modal for both create and update trades
    const createTradeHandlerModalOpen = () => {
        setCreateTradeModalOpen(true)
    }

    // Function for close the Modal for both create and update trades
    const createTradeHandlerModalClose = () => {
        setCreateTradeModalOpen(false)
        setIsUpdateModal(0)

        setTradeDataTime("");
        setStockName("")
        setListingPrice(0)
        setQuantity(0)
        setType("")
        setPricePerUnit(0)
    }


    // function for create new trade
    const createTradeSubmitHandler = async () => {
        console.log(tradeDataTime);
        console.log(stockName);
        console.log(listingPrice);
        console.log(quantity);
        console.log(type);
        console.log(pricePerUnit);

        if (!tradeDataTime || !stockName || !listingPrice || !quantity || !type || !pricePerUnit) {
            alert("Please Fill All Details")
            return
        }

        try {
            const { data } = await axios.post(`${API_URL}/api/create-trade`, {
                tradeDataTime, stockName, listingPrice, quantity, type, pricePerUnit
            })
            getAllTrades()
            createTradeHandlerModalClose()
            console.log(data);
        } catch (error) {
            console.log(error);
        }

    }

    // Function for Single Trade Details
    const getDataById = async (tradeId) => {

        try {
            const { data } = await axios.get(`${API_URL}/api/get-single-trade/${tradeId}`)
            console.log(data);

            setTradeDataTime(data?.tradeDataTime);
            setStockName(data?.stockName)
            setListingPrice(data?.listingPrice)
            setQuantity(data?.quantity)
            setType(data?.type)
            setPricePerUnit(data?.pricePerUnit)



        } catch (error) {
            console.log(error);

        }

    }


    // Function for fetching the single trade details by using id for assigning data for Update Modal 
    const updateTradeDetails = (tradeId) => {
        console.log(tradeId);
        setIsUpdateModal(tradeId);
        setTradeId(tradeId)
        createTradeHandlerModalOpen(true)

        getDataById(tradeId)

    }


    // Function for updating the single trade details
    const tradeUpdateHandler = async () => {
        if (!tradeDataTime || !stockName || !listingPrice || !quantity || !type || !pricePerUnit) {
            alert("Please Fill All Details")
            return;
        }

        console.log(tradeDataTime, stockName, listingPrice, quantity, type, pricePerUnit);

        try {
            const { data } = await axios.put(`${API_URL}/api/update-trade/${tradeId}`, {
                tradeDataTime, stockName, listingPrice, quantity, type, pricePerUnit
            })
            getAllTrades()
            createTradeHandlerModalClose()
            console.log(data);
        } catch (error) {
            console.log(error);
        }

    }


    // Function for deleting single trade details
    const deleteTradeDetails = async (tradeId) => {

        if (window.confirm("Are you sure to delete this trade ?")) {

            try {
                const data = await axios.delete(`${API_URL}/api/delete-trade/${tradeId}`)
                console.log(data);
                getAllTrades()
            } catch (error) {
                console.log(error);
            }
        }
    }

    const navigate = useNavigate()

    const createOrderHandler = (tradeId) => {



        const createOrder = async () => {
            try {
                const { apidata } = await axios.get(`${API_URL}/api/get-single-trade/${tradeId}`)

                try {
                    let status = "created"
                    const { data } = await axios.post(`${API_URL}/api/order/create-order`, {
                        quantity: apidata?.quantity, pricePerUnit: apidata?.pricePerUnit, type: apidata?.type, stockName: apidata?.stockName, status: status
                    })
                    console.log(data);
                    navigate("/orders")
                } catch (error) {
                    console.log(createOrder);
                }



            } catch (error) {
                console.log(error);

            }



        }

        if (window.confirm("Are you sure to create this order")) {

            alert("Please wait 2 more seconds we preparing your orders and we redirected to Orders Page")

            setTimeout(() => {

                createOrder()
            }, 2000)

        }


    }



    return (
        <>
            <div className='trade-details-container'>

                <div className='create-trade-container'>
                    <Button onClick={createTradeHandlerModalOpen} variant='contained'>Create Trade</Button>
                </div>

                {/* Create Trade Modal and Update Trade Modal */}

                <Modal open={createTradeModalOpen} onClose={createTradeHandlerModalClose}>
                    <Container sx={style} >
                        <div className='close-button'>
                            <Button onClick={createTradeHandlerModalClose} variant="contained" color="error">Close</Button>

                        </div>
                        <div className='input-fileds'>
                            <TextField label="Trade Data Time" type='date' sx={{ m: 1, width: '25ch' }}
                                InputProps={{
                                    startAdornment: <InputAdornment position="start"></InputAdornment>,
                                }}
                                value={tradeDataTime}
                                onChange={(e) => setTradeDataTime(e.target.value)}
                            />

                            <TextField label="Stock Name (Symbol)" type='text' sx={{ m: 1, width: '25ch' }}
                                value={stockName}
                                onChange={(e) => setStockName(e.target.value)}
                            />

                            <TextField label="Listing Price" type='number' sx={{ m: 1, width: '25ch' }}
                                value={listingPrice}
                                onChange={(e) => setListingPrice(e.target.value)}
                            />

                            <TextField label="Quantity" type='number' sx={{ m: 1, width: '25ch' }}
                                value={quantity}
                                onChange={(e) => setQuantity(e.target.value)}
                            />

                            <div>
                                <InputLabel sx={{ m: 1 }} id="demo-simple-select-label">Type</InputLabel>
                                <Select
                                    id="demo-simple-select"
                                    label="Type"
                                    sx={{ m: 1, width: '25ch' }}
                                    value={type}
                                    onChange={(e) => setType(e.target.value)}

                                >

                                    <MenuItem value={"buy"}>Buy</MenuItem>
                                    <MenuItem value={"sell"}>Sell</MenuItem>
                                </Select>
                            </div>


                            <TextField label="Price per Unit" type='number' sx={{ m: 1, width: '25ch' }}
                                value={pricePerUnit}
                                onChange={(e) => setPricePerUnit(e.target.value)}
                            />
                            {
                                isUpdateModal > 0 ? <Button onClick={tradeUpdateHandler} sx={{ ml: 15, mt: 1, p: 2 }} variant="contained" color="success">Update Trade</Button> : <Button onClick={createTradeSubmitHandler} sx={{ ml: 15, mt: 1, p: 2 }} variant="contained" color="success">Create Trade</Button>
                            }
                            {/* <Button onClick={createTradeSubmitHandler} sx={{ ml: 15, mt: 1, p: 2 }} variant="contained" color="success">Create Trade</Button> */}
                        </div>
                    </Container>
                </Modal>


                <div className='trade-details-table-container'>

                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 650 }} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell align='center'> <Typography className='table-header'>Trade Data Time</Typography> </TableCell>
                                    <TableCell align="center"> <Typography>Stock Name&nbsp;(symbol)</Typography> </TableCell>
                                    <TableCell align="center"> <Typography>Listing Price</Typography> </TableCell>
                                    <TableCell align="center"> <Typography>Quantity</Typography> </TableCell>
                                    <TableCell align="center"> <Typography>Type&nbsp;(buy / sell)</Typography> </TableCell>
                                    <TableCell align="center"> <Typography>Price Per Unit</Typography> </TableCell>
                                    <TableCell align="right"> <Typography>Actions</Typography> </TableCell>

                                </TableRow>
                            </TableHead>
                            <TableBody>

                                {
                                    tradeDetails.map((trade) => (
                                        <TableRow
                                            key={trade?.id}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell align="center" >
                                                {trade?.tradeDataTime}
                                            </TableCell>
                                            <TableCell align="center">{trade?.stockName}</TableCell>
                                            <TableCell align="center">{trade?.listingPrice}</TableCell>
                                            <TableCell align="center">{trade?.quantity}</TableCell>
                                            <TableCell align="center">{trade?.type}</TableCell>
                                            <TableCell align="center">{trade?.pricePerUnit}</TableCell>
                                            <TableCell align='center'><Button variant="contained" color="success" onClick={() => createOrderHandler(trade?.id)}>Order</Button></TableCell>
                                            <TableCell align='center'><Button variant="contained" color="secondary" onClick={() => updateTradeDetails(trade?.id)}>Update</Button></TableCell>
                                            <TableCell align='center'><Button variant="contained" color="error" onClick={() => deleteTradeDetails(trade?.id)}>Delete</Button></TableCell>

                                        </TableRow>
                                    ))
                                }



                            </TableBody>
                        </Table>
                    </TableContainer>

                </div>
            </div>
        </>
    )
}

export default TradeDetails