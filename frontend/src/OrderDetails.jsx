import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@mui/material'
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import API_URL from './api'

const OrderDetails = () => {
    const [orderDetailsData, setOrderDetailsData] = useState([])

    const fetchAllOrders = async () => {
        const { data } = await axios.get(`${API_URL}/api/order/all-orders`);
        console.log(data);
        setOrderDetailsData(data);
    }

    useEffect(() => {
        fetchAllOrders()
        console.log(orderDetailsData);

    }, [])


    return (
        <div className='trade-details-table-container'>

            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell align='center'> <Typography className='table-header'>Stock Name</Typography> </TableCell>
                            <TableCell align="center"> <Typography>Type</Typography> </TableCell>
                            <TableCell align="center"> <Typography>Price Per Unit</Typography> </TableCell>
                            <TableCell align="center"> <Typography>Quantity</Typography> </TableCell>
                            <TableCell align="center"> <Typography>Status</Typography> </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            orderDetailsData.map((order) => (
                                <TableRow
                                    key={order?.id}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                    <TableCell align="center" >
                                        {order?.stockName}
                                    </TableCell>
                                    <TableCell align="center">{order?.type}</TableCell>
                                    <TableCell align="center">{order?.pricePerUnit}</TableCell>
                                    <TableCell align="center">{order?.quantity}</TableCell>
                                    <TableCell align='center'><Button variant="contained" color="success" >Order {order?.status}</Button></TableCell>


                                </TableRow>
                            ))
                        }



                    </TableBody>
                </Table>
            </TableContainer>

        </div>
    )
}

export default OrderDetails