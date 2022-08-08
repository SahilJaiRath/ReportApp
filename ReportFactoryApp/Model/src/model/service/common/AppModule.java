package model.service.common;

import java.util.Date;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jul 21 09:31:26 IST 2022
// ---------------------------------------------------------------------
public interface AppModule extends ApplicationModule {
    void callAssemblyJobCardStatusReport(String LV_UNIT, String LV_Asmbly_JCNo, String LV_AsmblyJC_Prod_Cd, Date FRDATE,
                                         Date TODATE);

    void callAssemblyProductionDetail(String LV_UNIT, String LV_Product_cd, Date FRDATE, Date TODATE);

    void callAssemblyProductionSummary(String LV_UNIT, Date FRDATE, Date TODATE, String LV_PRODUCT);

    void callB2CSaleInvoiceUnRegistered(String LV_UNIT, Date FRDATE, Date TODATE);

    void callB2bSaleInvoiceRegistered(String LV_UNIT, String CUSTOMER, Date FRDATE, Date TODATE);

    void callBMRBOMTree(String LV_UNIT, String LV_BMR_BT_BOMITEM);

    void callBMRandBPRBatchStatus(String LV_UNIT);

    void callBOMTree(String LV_BOMTREE_PRD_CD, String LV_REQUESTQTY);

    void callBOMVSActualComsumption(String LV_UNIT);

    void callBankRecociliation(String LV_GL, Date FRDATE, Date TODATE);

    void callBatchWiseBPRConsumption(String LV_UNIT, String LV_ProductName, String LV_BatchNo);

    void callBatchwiseBMRConsumption(String LV_UNIT, Date FRDATE, Date TODATE, String LV_PARTNAME, String LV_BATCHNO);

    void callBatchwiseitemStockSummary(String LV_UNIT);

    void callBillOfMaterial(String LV_BOM_PRODUCT);

    void callCollectionReport(String LV_UNIT, Date FRDATE, Date TODATE, String LV_CollectionCust);

    void callConsolidatePurchaseDetailforTDSPANBased(Date FRDATE, Date TODATE);

    void callContractOrder(String LV_UNIT, Date FRDATE, Date TODATE);

    void callCreditorOutstanding(String LV_UNIT, Integer LV_SID, Date FRDATE, String LV_Custcode, Integer LV_p1,
                                 Integer LV_p2, Integer LV_p3, Integer LV_p4, Integer LV_p5, Integer LV_p6);

    void callCreditorOutstandingSummary(String LV_UNIT, Integer LV_SID, Date FRDATE, String LV_Custcode, Integer LV_p1,
                                        Integer LV_p2, Integer LV_p3, Integer LV_p4, Integer LV_p5, Integer LV_p6);

    void callDailyInvoiceSale(String LV_UNIT, Date FRDATE, Date TODATE, String LV_INVOICENO);

    void callDebitCreditNote(String LV_TYPE, Date FRDATE, Date TODATE, String LV_UNIT);

    void callDebitCreditNoteEntryDetail(String LV_UNIT, Date FRDATE, Date TODATE);

    void callDebtorOutstanding(String LV_UNIT, Integer LV_SID, Date FRDATE, String LV_CUSTOMER, Integer LV_p1,
                               Integer LV_p2, Integer LV_p3, Integer LV_p4, Integer LV_p5, Integer LV_p6);

    void callDebtorOutstandingSummary(String LV_UNIT, Integer LV_SID, Date FRDATE, String LV_CUSTOMER, Integer LV_p1,
                                      Integer LV_p2, Integer LV_p3, Integer LV_p4, Integer LV_p5, Integer LV_p6);

    void callDepartmentWiseIssueSummary(String LV_UNIT, Date FRDATE, Date TODATE);

    void callDispatchAdviceDetail(String LV_DA_NO, Date FRDATE, Date TODATE);

    void callEInvoice(String LV_INVOICE, Date FRDATE, Date TODATE);

    void callEWayBillRegister(String LV_UNIT, Date FRDATE, Date TODATE);

    void callFGExpiredStock(String LV_UNIT);

    void callFGTransfer(String LV_UNIT, Date FRDATE, Date TODATE, String LV_ChallanType);

    void callFGYieldProduction(String LV_UNIT, String LV_PRODUCTCD);

    void callGateEntryDetail(String LV_UNIT, Date FRDATE, Date TODATE);

    void callGatePassChallan(String LV_UNIT, Date FRDATE, Date TODATE);

    void callGenLedgerproc(String LV_UNIT, String LV_GLCD, String LV_SID, String LV_YEAR, Date FRDATE, Date TODATE);

    void callHSNSummary(String LV_TYPE, Date FRDATE, Date TODATE, String LV_UNIT);

    void callIncomingInspectionRegister(String LV_UNIT, Date FRDATE, Date TODATE);

    void callIndentPendingAndClose(String LV_TYPE, Date FRDATE, Date TODATE, String LV_UNIT);

    void callInterUnitTransitReport(String LV_INVUNIT, String LV_BILLUNIT);

    void callInvoiceWiseSale(String LV_UNIT, String LV_ItemName, Date FRDATE, Date TODATE);

    void callIssueBreakup(String LV_UNIT, String LV_LOC, Integer LV_SID, Date FRDATE, Date TODATE);

    void callIssueDetail(String LV_UNIT, Date FRDATE, Date TODATE);

    void callIssueStatusBMR(String LV_UNIT, Date FRDATE, Date TODATE);

    void callIssueStatusBPR(String LV_UNIT, Date FRDATE, Date TODATE);

    void callItemWhereused(String LV_PartNameFilter);

    void callItemWiseSale(String LV_UNIT, Date FRDATE, Date TODATE, String LV_INVOICETYPE, String LV_STOCKTYPE,
                          String LV_PRODUCTCODE);

    void callItemsDetailafterQC(String LV_UNIT, Date FRDATE, Date TODATE, String LV_ITEMCD);

    void callJVSalePurchaseContraBook(String LV_UNIT, Date FRDATE, Date TODATE);

    void callJobCardDetailStatus(String LV_UnitCode);

    void callJobworkRegister(String LV_PO, Date FRDATE, Date TODATE, String LV_UNIT);

    void callLotStock(String LV_UNIT, String LV_ItemType);

    void callNonMovingItem(String LV_UNIT, Date LV_ASONDT, String LV_DAYS, String LV_GROUPCODE, String LV_NEW,
                           String LV_SUBGROUP);

    void callPartBalProc(String LV_UNIT, String LV_GL_TYPE, String LV_SID, String LV_YEAR, Date FRDATE, Date TODATE);

    void callPendingBMRforQC(String LV_UNIT, String LV_BatchFilter);

    void callPendingDispatchAdviceForInvoice(String LV_UNIT, Date FRDATE, Date TODATE);

    void callPendingFGforReceivingInStore(String LV_UNIT);

    void callPendingFinishGoodsBatchWise(String LV_UNIT);

    void callPendingGateEntryForSRV(String LV_UNIT);

    void callPendingIndentforPO(String LV_UNIT, Date FRDATE, Date TODATE);

    void callPendingJobcardforApproval(String LV_UNIT, String LV_JCSTATUS);

    void callPendingPurchaseOrder(String LV_UNIT, Date FRDATE, Date TODATE, String LV_ITEMGROUP, String LV_APPSTATUS,
                                  String LV_VALIDTILL);

    void callPendingSRVForQc(String LV_UNIT, Date FRDATE, Date TODATE, String LV_GroupCD, String LV_SubGroupCd,
                             String LV_VendorCd);

    void callPendingToD28(String LV_UNIT, String LV_ChallanNo, String LV_ItemCode);

    void callProcessInspectionReport(String LV_UNIT, Date FRDATE, Date TODATE, String LV_PRODCD, String LV_PROCESS);

    void callProcessProduction(String LV_UNIT, Date FRDATE, Date TODATE);

    void callProductShortageandBatch(String LV_UNIT, String LV_PRODUCT, String LV_REQUESTQTY, String LV_SID);

    void callProductionStatistics(String LV_UNIT, Date FRDATE, Date TODATE);

    void callPurchaseIndent(String LV_UNIT, String LV_ApprovedBy, Date FRDATE, Date TODATE);

    void callPurchaseIndentRegister(String LV_UNIT, Date FRDATE, Date TODATE);

    void callPurchaseOrder(String LV_UNIT, Date FRDATE, Date TODATE);

    void callPurchaseOrderRegister(String LV_UNIT, Date FRDATE, Date TODATE);

    void callPurchaseRegister(String LV_UNIT, Integer LV_SID, String LV_VendorCode, String LV_InterUnit,
                              String LV_NatureOfPurchase, Date FRDATE, Date TODATE);

    void callPurchaseRegisterIncludingDRandCRNote(String LV_UNIT, String LV_DOC_TYPE, Date FRDATE, Date TODATE);

    void callQualityInProcess(String LV_UNIT, String LV_Item_Name);

    void callRMVendorWiseAnalysis(String LV_UNIT, Date FRDATE, Date TODATE);

    void callReturnSlip(String LV_UNIT, Date FRDATE, Date TODATE);

    void callSRVRejectionRegister(String LV_UNIT, Date FRDATE, Date TODATE, String LV_TYPE);

    void callSTOfromSalesWarehouse(String LV_WAREHOUSENAME);

    void callSaleOrder(String LV_UNIT, Date FRDATE, Date TODATE);

    void callSaleOrderDetail(String LV_UNIT, Date FRDATE, Date TODATE, String LV_PoNo);

    void callSaleRegisterDetail4(String LV_UNIT, Integer LV_SID, String LV_INC_CAN, Date FRDATE, Date TODATE);

    void callSaleRegisterDetailIncDrAndCr(String LV_UNIT, Integer LV_SID, String LV_STATUS, Date FRDATE, Date TODATE);

    void callSaleSummary(String LV_UNIT, Integer LV_SID, Date FRDATE, Date TODATE);

    void callStatementofDispatch(String LV_UNIT, Date FRDATE, Date TODATE);

    void callStockAdjustment(String LV_UNIT, String LV_PartName);

    void callStockLedger3(String LV_UNIT, String LV_ITEM_CD, String LV_SID, String LV_LOC, Date FRDATE, Date TODATE,
                          String LV_GROUP_CD, String LV_SUB_GROUP_CD);

    void callStockStatus(String LV_UNIT, String LV_ITEM_CD, String LV_FIN_YEAR);

    void callSubLedger(String LV_UNIT, Integer LV_SID, String LV_NameFilter, Date FRDATE, Date TODATE);

    void callSubLedgerproc(String LV_UNIT, String LV_GLCD, String LV_SID, String LV_YEAR, Date FRDATE, Date TODATE);

    void callTDSReport(String LV_UNIT, Integer LV_SID, Date FRDATE, Date TODATE);

    void callTRFRegister(String LV_UNIT, String LV_ProductFilter, Date FRDATE, Date TODATE);

    void callVendorRejection(String LV_UNIT, Date FRDATE, Date TODATE);

    void callVoucherDetail(String LV_UNIT, Date FRDATE, Date TODATE);

    void callVoucherWiseDebitCredit(String LV_UNIT);

    void callWIPLedgerreport(String LV_UNIT, String LV_LocWip, String LV_WIPLgr_PrcssFilter,
                             String LV_WIPLgr_ItemFilter, Integer LV_SID, Date FRDATE, Date TODATE);

    void callWIPStock(String LV_UNIT, String LOC, Date FRDATE, Date TODATE);

    void getData();

    void getFGdata();

    void callItemWiseTotalInventory(String LV_UNIT, String LV_PRODUCTCODE);

    void callRequisitionSlipRegister(String LV_UNIT, String ReqTypeFilter);

    void callJobworkReciptAgainstChallanOutward(String LV_UNIT, Date FRDATE, Date TODATE);

    void callJobworkChallanOutward(String LV_UNIT, Date FRDATE, Date TODATE, String LV_PRODUCTCD);

    void callPendingChallanforReceive(String LV_UNIT, Date FRDATE, Date TODATE);

    void callARNumberAgainstMRNITEM(String LV_UNIT, String LV_GroupCd, String LV_SubGroupCd, String LV_ItemName,
                                    Date FRDATE, Date TODATE);

    void callPendingMainStoreReceiving(String LV_UNIT);

    void callGoodsinTransit(String LV_UNIT, Date FRDATE, Date TODATE, String LV_CHALLANNO);

    void callPendingMRNforPurchaseInvoice(Date FRDATE, Date TODATE);

    void callSRVDetail(String LV_UNIT, String LV_LocationFilter, String LV_ITEM, String LV_GroupCd,
                       String LV_SubGroupCd, String LV_ApproveFilter, String LV_Statusfilter, Date FRDATE, Date TODATE);

    void callTrialBalance(String LV_UNIT, Integer LV_SID, Date FRDATE, Date TODATE);

    void callFGStockproc(String LV_UNIT, String LV_SID, Date FRDATE, Date TODATE);

    void callDailyStockStatementproc(String LV_UNIT, String LV_SID, String LV_LocationFilter, Date FRDATE);

    void callApprovedPurchaseIndent(String LV_UNIT, Date FRDATE, Date TODATE);

    void callShortExcessRegister(String LV_UNIT, Date FRDATE, Date TODATE, String LV_REPTYPE);

    void callSummaryOfDispatch(String LV_UNIT, Date FRDATE, Date TODATE);
}

