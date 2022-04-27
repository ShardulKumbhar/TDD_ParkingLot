package com.bridgelabzparkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot implements IParkingLot {

    final int PARKING_LOT_CAPACITY = 2;
    private final List<IParkingMonitor> monitors = new ArrayList<>();
    private final Map<String, Car> parkingMap = new HashMap<>();

    /**
     * Method To Park The Car.
     * If array size is less than capacity then store id and car name
     * if array size is equals or gretter shows lot full Exception
     *
     * @param car Object
     * @throws ParkingLotException LOT FULL
     */
    @Override
    public void parkVehicle(Car car) throws ParkingLotException {
        if (this.parkingMap.size() < PARKING_LOT_CAPACITY) {
            parkingMap.put(car.getID(), car);
        } else if (this.parkingMap.size() == PARKING_LOT_CAPACITY) {
            this.notifyToMonitor();
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOT_FULL);
        }
        if (this.parkingMap.size() == PARKING_LOT_CAPACITY) {
            this.notifyToMonitor();
        }
    }

    /**
     * Method To Un-Park The Car.
     *if car=null shows empty parking(no such vehicle) throws Exception
     * if parked car and given car id is different shows mismatch exception
     * else car with matching id get remove or unparked
     * @param car object
     * @throws ParkingLotException NO SUCH VEHICLE
     */
    @Override
    public void unParkVehicle(Car car) throws ParkingLotException {
        if (car == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE);
        if (!parkingMap.containsKey(car.getID()))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_MISMATCH);
        parkingMap.remove(car.getID());
    }

    /**
     * Method To Add Monitors
     *
     * @param monitor
     */
    @Override
    public void addMonitor(IParkingMonitor monitor)
    {
        this.monitors.add(monitor);
    }

    /**
     * Method To Update Message To The Monitor
     */
    @Override
    public void notifyToMonitor() {
        for (IParkingMonitor monitor : monitors) {
            monitor.updateMessage("Parking Lot Is Full");
        }
    }

    /**
     * Method to Check is Car Is Parked or Not.
     *
     * @return boolean value
     */
    @Override
    public boolean isParked(Car car)
    {
        return parkingMap.containsKey(car.getID());
    }

    /**
     * Method To check if Car is Un-Parked or Not.
     *
     * @return boolean value
     */
    @Override
    public boolean isUnParked(Car car)
    {
        return (!parkingMap.containsKey(car.getID()));
    }
}
