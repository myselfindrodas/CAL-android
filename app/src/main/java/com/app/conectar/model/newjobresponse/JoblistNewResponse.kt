package com.app.conectar.model.newjobresponse

import com.google.gson.annotations.SerializedName

data class JoblistNewResponse(
    @SerializedName("result")
    val resultjoblistnew: resultjoblistnew? = null,
    @SerializedName("status")
    val statusjoblistnew: statusjoblistnew? = null
)

data class statusjoblistnew(


    @field:SerializedName("error_code")
    val error_code: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    )

data class resultjoblistnew(
    @SerializedName("new_joblist")
    val newJoblist: List<NewJoblist>
)


data class NewJoblist(
    @SerializedName("builder_otp")
    val builderOtp: Any? = null, // null
    @SerializedName("commission_value")
    val commissionValue: String? = null, // :
    @SerializedName("created_by")
    val createdBy: String? = null, // 0
    @SerializedName("created_ts")
    val createdTs: String? = null, // 2022-09-27 08:14:23
    @SerializedName("delivery_date")
    val deliveryDate: String? = null, // 2022-10-05
    @SerializedName("delivery_time")
    val deliveryTime: String? = null, // 15:00:00
    @SerializedName("Destination")
    val destination: String? = null, // Taj Mahal
    @SerializedName("destination_lat")
    val destinationLat: String? = null, // 27.1751448
    @SerializedName("destination_long")
    val destinationLong: String? = null, // 78.0421422
    @SerializedName("drop_location_company_name")
    val dropLocationCompanyName: String? = null, // DFR PVT LTD
    @SerializedName("drop_location_contact_person_number")
    val dropLocationContactPersonNumber: String? = null, // Himesh
    @SerializedName("drop_location_email")
    val dropLocationEmail: String? = null, // himesh@gmail.com
    @SerializedName("drop_location_main_number")
    val dropLocationMainNumber: String? = null, // 8956234712
    @SerializedName("Equipment")
    val equipment: String? = null, // 1
    @SerializedName("equipment_name")
    val equipmentName: Any? = null, // null
    @SerializedName("Equipment_value")
    val equipmentValue: String? = null, // Excavator Small
    @SerializedName("final_unitprice")
    val finalUnitprice: String? = null, // 110.00
    @SerializedName("FreightCharges")
    val freightCharges: String? = null, // 0
    @SerializedName("invoice")
    val invoice: String? = null,
    @SerializedName("is_active")
    val isActive: String? = null, // 1
    @SerializedName("is_save")
    val isSave: String? = null, // 1
    @SerializedName("job_completed_date")
    val jobCompletedDate: String? = null, // 0000-00-00 00:00:00
    @SerializedName("JobEstimatePrice")
    val jobEstimatePrice: String? = null, // 440.0
    @SerializedName("job_id")
    val jobId: String? = null, // 2
    @SerializedName("job_status")
    val jobStatus: String? = null, // 1
    @SerializedName("job_type")
    val jobType: String? = null, // G
    @SerializedName("jobhauloff_type")
    val jobhauloffType: String? = null,
    @SerializedName("jobload_type")
    val jobloadType: String? = null, // Load
    @SerializedName("lib_information")
    val libInformation: String? = null,
    @SerializedName("loadspacing_minutes")
    val loadspacingMinutes: String? = null, // 10
    @SerializedName("LodeType")
    val lodeType: String? = null, // test_load
    @SerializedName("material_cont")
    val materialCont: String? = null,
    @SerializedName("material_name")
    val materialName: String? = null, // River Rock
    @SerializedName("MaterialType")
    val materialType: String? = null, // 7
    @SerializedName("MaterialType_other")
    val materialTypeOther: String? = null,
    @SerializedName("MaterialoptionType")
    val materialoptionType: String? = null, // 0
    @SerializedName("materials_available")
    val materialsAvailable: String? = null,
    @SerializedName("NoOfTrucks")
    val noOfTrucks: String? = null, // 4
    @SerializedName("notes")
    val notes: String? = null, // RTFG
    @SerializedName("order_number")
    val orderNumber: String? = null,
    @SerializedName("payment_date")
    val paymentDate: String? = null, // 0000-00-00 00:00:00
    @SerializedName("payment_status")
    val paymentStatus: String? = null, // 0
    @SerializedName("payment_type")
    val paymentType: String? = null,
    @SerializedName("perloadweight")
    val perloadweight: String? = null, // 18.00
    @SerializedName("pickup_date")
    val pickupDate: String? = null, // 2022-10-01
    @SerializedName("pickup_location_company_name")
    val pickupLocationCompanyName: String? = null, // JK Cement
    @SerializedName("pickup_location_contact_person_number")
    val pickupLocationContactPersonNumber: String? = null, // Rohit Jani
    @SerializedName("pickup_location_email")
    val pickupLocationEmail: String? = null, // rohit@gmail.com
    @SerializedName("pickup_location_main_number")
    val pickupLocationMainNumber: String? = null, // 6345634523
    @SerializedName("pickup_time")
    val pickupTime: String? = null, // 15:00:00
    @SerializedName("priority")
    val priority: String? = null, // 0
    @SerializedName("savings")
    val savings: String? = null, // 0.00
    @SerializedName("SchedulingType")
    val schedulingType: String? = null,
    @SerializedName("Source")
    val source: String? = null, // Jasoda Nagar Cross Road
    @SerializedName("source_lat")
    val sourceLat: String? = null, // 22.9830622
    @SerializedName("source_long")
    val sourceLong: String? = null, // 72.6229687
    @SerializedName("spec_sheet")
    val specSheet: String? = null,
    @SerializedName("spreading_equipment")
    val spreadingEquipment: String? = null, // 0
    @SerializedName("spreading_equipment_name")
    val spreadingEquipmentName: String? = null,
    @SerializedName("spreading_equipment_other")
    val spreadingEquipmentOther: String? = null,
    @SerializedName("suggested_marketrate")
    val suggestedMarketrate: String? = null, // 100.00
    @SerializedName("supplier_otp")
    val supplierOtp: Any? = null, // null
    @SerializedName("total_mileage")
    val totalMileage: String? = null, // 50
    @SerializedName("total_per_job_load_cost")
    val totalPerJobLoadCost: String, // 4
    @SerializedName("totaljobcost")
    val totaljobcost: String? = null, // 440.00
    @SerializedName("totalmiles")
    val totalmiles: String? = null, // 445.85
    @SerializedName("truck_name")
    val truckName: String? = null, // Quint Dump Truck (5 axles) (18 tons)
    @SerializedName("TruckType")
    val truckType: String? = null, // 15
    @SerializedName("TruckType_other")
    val truckTypeOther: String? = null,
    @SerializedName("truckimage")
    val truckimage: String? = null, // uploads/truck_image/1663532262.jpg
    @SerializedName("trucktype_ids")
    val trucktypeIds: String? = null,
    @SerializedName("unique_ids")
    val uniqueIds: String? = null, // #CAL68249
    @SerializedName("unit_price")
    val unitPrice: String? = null, // 110.00
    @SerializedName("updated_by")
    val updatedBy: String? = null, // 0
    @SerializedName("updated_ts")
    val updatedTs: String? = null, // 2022-09-28 12:22:25
    @SerializedName("user_master_id")
    val userMasterId: String? = null, // 1821
    @SerializedName("Weight")
    val weight: String? = null // 72
)