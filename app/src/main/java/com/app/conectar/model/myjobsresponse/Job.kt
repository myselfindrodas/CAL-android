package com.app.conectar.model.myjobsresponse
import com.google.gson.annotations.SerializedName

data class Job(
    @SerializedName("available_load")
    val availableLoad: String, // 4
    @SerializedName("builder_otp")
    val builderOtp: Any?, // null
    @SerializedName("commission_value")
    val commissionValue: String, // :
    @SerializedName("created_by")
    val createdBy: String, // 0
    @SerializedName("created_ts")
    val createdTs: String, // 2022-10-06 22:30:54
    @SerializedName("delivery_date")
    val deliveryDate: String, // 2022-10-07
    @SerializedName("delivery_time")
    val deliveryTime: String, // 22:00:00
    @SerializedName("Destination")
    val destination: String, // 816 Camaron St, San Antonio, TX 78212, USA
    @SerializedName("destination_lat")
    val destinationLat: String, // 29.4328789
    @SerializedName("destination_long")
    val destinationLong: String, // -98.5002828
    @SerializedName("drop_location_company_name")
    val dropLocationCompanyName: String, // drop off LLC
    @SerializedName("drop_location_contact_person_number")
    val dropLocationContactPersonNumber: String, // Mr drop off
    @SerializedName("drop_location_email")
    val dropLocationEmail: String, // em@email.com
    @SerializedName("drop_location_main_number")
    val dropLocationMainNumber: String, // 2106453125
    @SerializedName("Equipment")
    val equipment: String, // 0
    @SerializedName("equipment_name")
    val equipmentName: Any?, // null
    @SerializedName("Equipment_value")
    val equipmentValue: String,
    @SerializedName("final_unitprice")
    val finalUnitprice: String, // 100.00
    @SerializedName("FreightCharges")
    val freightCharges: String, // 0
    @SerializedName("invoice")
    val invoice: String,
    @SerializedName("is_active")
    val isActive: String, // 1
    @SerializedName("is_save")
    val isSave: String, // 0
    @SerializedName("job_completed_date")
    val jobCompletedDate: String, // 0000-00-00 00:00:00
    @SerializedName("JobEstimatePrice")
    val jobEstimatePrice: String, // 500.0
    @SerializedName("job_id")
    val jobId: String, // 17
    @SerializedName("job_status")
    val jobStatus: String, // 1
    @SerializedName("job_type")
    val jobType: String, // G
    @SerializedName("jobhauloff_type")
    val jobhauloffType: String,
    @SerializedName("jobload_type")
    val jobloadType: String, // Load
    @SerializedName("lib_information")
    val libInformation: String,
    @SerializedName("loadspacing_minutes")
    val loadspacingMinutes: String, // 20
    @SerializedName("LodeType")
    val lodeType: String, // test_load
    @SerializedName("material_cont")
    val materialCont: String,
    @SerializedName("material_name")
    val materialName: String, // Gravel
    @SerializedName("MaterialType")
    val materialType: String, // 4
    @SerializedName("MaterialType_other")
    val materialTypeOther: String,
    @SerializedName("MaterialoptionType")
    val materialoptionType: String, // 0
    @SerializedName("materials_available")
    val materialsAvailable: String,
    @SerializedName("NoOfTrucks")
    val noOfTrucks: String, // 5
    @SerializedName("notes")
    val notes: String, // more noted
    @SerializedName("order_number")
    val orderNumber: String,
    @SerializedName("payment_date")
    val paymentDate: String, // 0000-00-00 00:00:00
    @SerializedName("payment_status")
    val paymentStatus: String, // 0
    @SerializedName("payment_type")
    val paymentType: String,
    @SerializedName("perloadweight")
    val perloadweight: String, // 25.00
    @SerializedName("pickup_date")
    val pickupDate: String, // 2022-10-07
    @SerializedName("pickup_location_company_name")
    val pickupLocationCompanyName: String, // pickup LLC
    @SerializedName("pickup_location_contact_person_number")
    val pickupLocationContactPersonNumber: String, // Mr pickup
    @SerializedName("pickup_location_email")
    val pickupLocationEmail: String, // hello@email.com
    @SerializedName("pickup_location_main_number")
    val pickupLocationMainNumber: String, // 2101231234
    @SerializedName("pickup_time")
    val pickupTime: String, // 05:00:00
    @SerializedName("priority")
    val priority: String, // 0
    @SerializedName("savings")
    val savings: String, // 50.00
    @SerializedName("SchedulingType")
    val schedulingType: String,
    @SerializedName("self_job")
    val selfJob: String, // 1
    @SerializedName("Source")
    val source: String, // 614 W French Pl, San Antonio, TX 78212, USA
    @SerializedName("source_lat")
    val sourceLat: String, // 29.4497947
    @SerializedName("source_long")
    val sourceLong: String, // -98.50012369999999
    @SerializedName("spec_sheet")
    val specSheet: String,
    @SerializedName("spreading_equipment")
    val spreadingEquipment: String, // 0
    @SerializedName("spreading_equipment_name")
    val spreadingEquipmentName: String,
    @SerializedName("spreading_equipment_other")
    val spreadingEquipmentOther: String,
    @SerializedName("suggested_marketrate")
    val suggestedMarketrate: String, // 100.00
    @SerializedName("supplier_id")
    val supplierId: String, // 1821
    @SerializedName("supplier_otp")
    val supplierOtp: Any?, // null
    @SerializedName("total_load")
    val totalLoad: String, // 5
    @SerializedName("total_mileage")
    val totalMileage: String, // 1.88
    @SerializedName("total_per_job_load_cost")
    val totalPerJobLoadCost: String, // 5
    @SerializedName("totaljobcost")
    val totaljobcost: String, // 500.00
    @SerializedName("totalmiles")
    val totalmiles: String, // 1.17
    @SerializedName("truck_details")
    val truckDetails: List<TruckDetail>,
    @SerializedName("truck_name")
    val truckName: String, // Super Dump Truck (7 axles) (25 tons)
    @SerializedName("TruckType")
    val truckType: String, // 11
    @SerializedName("TruckType_other")
    val truckTypeOther: String,
    @SerializedName("truckimage")
    val truckimage: String, // uploads/truck_image/1663200008.jpg
    @SerializedName("trucktype_ids")
    val trucktypeIds: String,
    @SerializedName("unique_ids")
    val uniqueIds: String, // #CAL18635
    @SerializedName("unit_price")
    val unitPrice: String, // 90.00
    @SerializedName("updated_by")
    val updatedBy: String, // 0
    @SerializedName("updated_ts")
    val updatedTs: String, // 2022-10-10 12:30:30
    @SerializedName("user_master_id")
    val userMasterId: String, // 1839
    @SerializedName("Weight")
    val weight: String // 125
)