import { Passanger } from "./passanger.model";

export class bookingDetails{
    id:any;
    bookingId: any;
    bookingDate: any;
    scheduleId: any;
    flightNumber: any;
    flightName: any;
    arrivalDate:any;
    departureDate:any;
    arrivalTime: any;
    departureTime: any;
    arrivalLocation: any;
    departureLocation: any;
    seatClass: any;
    status: any;
    passenger:Passanger[] = [];
    totalAmount:any;
}