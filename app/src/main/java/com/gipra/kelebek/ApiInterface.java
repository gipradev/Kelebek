package com.gipra.kelebek;

import com.gipra.kelebek.District.ResponseDistrict;
import com.gipra.kelebek.Gene.ResponseGene;
import com.gipra.kelebek.MyProfile.ResponseAadharUpload;
import com.gipra.kelebek.MyProfile.ResponseAccountProof;
import com.gipra.kelebek.MyProfile.ResponseBankEditProfile;
import com.gipra.kelebek.MyProfile.ResponseBankProofView;
import com.gipra.kelebek.MyProfile.ResponseEditProfile;
import com.gipra.kelebek.MyProfile.ResponseKycView;
import com.gipra.kelebek.MyProfile.ResponsePanUpload;
import com.gipra.kelebek.MyProfile.ResponsePanView;
import com.gipra.kelebek.MyProfile.ResponseViewProfile;
import com.gipra.kelebek.State.ResponseState;
import com.gipra.kelebek.ui.Genealogy.ResponseNewEntry;
import com.gipra.kelebek.ui.Genealogy.ResponseSponsorTree;
import com.gipra.kelebek.ui.IncomeDetails.DirectSalesBonus.ResponseDirectSales;
import com.gipra.kelebek.ui.IncomeDetails.DirectSalesBonus.ResponseDirectSalesLoad;
import com.gipra.kelebek.ui.IncomeDetails.First_Purchase_BV_Report.ResponseFirstPurchaseSearch;
import com.gipra.kelebek.ui.IncomeDetails.LeaderSuccessBonus.ResponseLsbsSearch;
import com.gipra.kelebek.ui.IncomeDetails.Leader_Level_BV.ResponseLeaderLevelBv;
import com.gipra.kelebek.ui.IncomeDetails.Repurchase_BV_Report.ResponseRepurchaseBvReport;
import com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income.ResponseRepurchaseIncome;
import com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income_details.ResponseRepurchaseIncomeDetails;
import com.gipra.kelebek.ui.IncomeDetails.Team_Sales_BV_matching.ResponseTeamSalesBvMatching;
import com.gipra.kelebek.ui.IncomeDetails.Team_Sales_Bonus_details.ResponseTeamSalesBonus;
import com.gipra.kelebek.ui.PinManagement.ResponseOtrUpload;
import com.gipra.kelebek.ui.PinManagement.ResponseOtrView;
import com.gipra.kelebek.ui.PinManagement.ResponsePinProduct;
import com.gipra.kelebek.ui.PinManagement.ResponsePinRequest;
import com.gipra.kelebek.ui.PinManagement.ResponsePinSearch;
import com.gipra.kelebek.ui.Report.LeftSideSales.ResponseSales;
import com.gipra.kelebek.ui.Report.My_Product.ResponseGetBill;
import com.gipra.kelebek.ui.Report.My_Product.ResponseGetProduct;
import com.gipra.kelebek.ui.Report.My_Product.ResponseMyProduct;
import com.gipra.kelebek.ui.News.ResponseNews;
import com.gipra.kelebek.ui.Report.SponsorList.ResponseSponsorSearch;
import com.gipra.kelebek.ui.ResponseSponsorCheck;
import com.gipra.kelebek.ui.ResponseUplineCheck;
import com.gipra.kelebek.ui.UpgradeBA.ResponseUpgradeBA;
import com.gipra.kelebek.ui.UpgradeBA.ResponseUplinePost;
import com.gipra.kelebek.ui.UpgradeBA.ResponseViewUpline;
import com.gipra.kelebek.ui.Wallet.ResponseIncomeWallet;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @POST("State_list")
    Call<ResponseState>loadstatelist();

    @FormUrlEncoded
    @POST("District_list")
    Call<ResponseDistrict>loaddistrictlist(@Field("state_name")String sname);


    @FormUrlEncoded
    @POST("Outer_registration")
    Call<ResponseUserRegistration>UserRegistration(@Field("c_sponsor_name")String sponser_uid,
                                                   @Field("c_name")String name,
                                                   @Field("c_dob")String dob,
                                                   @Field("c_address")String address,
                                                   @Field("c_country")String country,
                                                   @Field("C_STATE")String state,
                                                   @Field("C_DISTRICT")String district,
                                                    @Field("n_pincode")String zipcode,
                                                   @Field("n_mobile")String mobile,
                                                   @Field("c_email")String email,
                                                   @Field("c_username")String username,
                                                   @Field("c_password")String password);
    @FormUrlEncoded
    @POST("Ba_registration")
    Call<ResponseRegBA>RegBA(@Field("C_FNAME")String cfname,
                             @Field("C_EMAIL")String cemail,
                             @Field("C_MOBILE")String cmobile,
                             @Field("C_GENDER")String cgender,
                             @Field("d_dob")String dob,
                             @Field("C_ADDRESS1")String caddress,
                             @Field("C_ZIP_CODE")String czip,
                             @Field("C_COUNTRY")String ccountry,
                             @Field("C_STATE")String cstate,
                             @Field("c_district")String cdist,
                             @Field("N_REF_ID")String nref,
                             @Field("C_PASSWORD")String cpsd,
                             @Field("C_PASSWORD1")String psdd,
                             @Field("c_username")String cuser,
                             @Field("C_PAN")String cpan,
                             @Field("c_nominee_name")String cnom,
                             @Field("c_nominee_relation")String cnomrela);
    @FormUrlEncoded
    @POST("Login")
    Call<ResponseLogin>login(@Field("username")String username,
                             @Field("password")String psd);
    @FormUrlEncoded
    @POST("Login_check")
    Call<ResponseLoginCheck>LoginCheck(@Field("username")String username);

    @FormUrlEncoded
    @POST("Logout")
    Call<ResponseLogout>Logout(@Field("username")String username);

    @Multipart
    @POST("User_image_upload")
    Call<ResponseUserImage>userimageupload(@Part("c_profile_photo\";filename=\"myfile.jpg\"") RequestBody file,
                                           @Part("member_id")int memberid);
    @FormUrlEncoded
    @POST("User_image_view")
    Call<ResponseUserImageView>userImageView(@Field("user_id")int userid);
    @FormUrlEncoded
    @POST("Profile_personal_info")
    Call<ResponseViewProfile>viewprofile(@Field("userid")int userid);
    @FormUrlEncoded
    @POST("Edit_profile")
    Call<ResponseEditProfile>editprofile( @Field("member_id") String u,
                                          @Field("c_dob") String dob,
                                         @Field("c_mobile") String mob,
                                         @Field("c_email") String email,
                                         @Field("c_address") String addr,
                                         @Field("c_country") String country,
                                         @Field("c_state") String state,
                                         @Field("C_DISTRICT") String dist,
                                         @Field("c_zipcode") String zip,
                                         @Field("n_pancard") String pan,
                                         @Field("n_kyc_number") String kyc);

    @FormUrlEncoded
    @POST("Edit_bank_details")
    Call<ResponseBankEditProfile>BankEditProfile(@Field("member_id")String userid,
                                                 @Field("c_bank_name")String bank,
                                                 @Field("c_branch")String branch,
                                                 @Field("n_account_number")String accnum,
                                                 @Field("c_ifsc_code")String ifsc);
    @Multipart
    @POST("PAN_uploading")
    Call<ResponsePanUpload>Panupload(@Part("C_PANCARD_FILE\";filename=\"myfile.jpg\"") RequestBody file,
                                     @Part("member_id") int userid);
    @Multipart
    @POST("Kyc_uploading")
    Call<ResponseAadharUpload>Aadharupload(@Part("C_Kyc_FILE\";filename=\"myfile.jpg\"") RequestBody file,
                                           @Part("member_id")int userid);
    @Multipart
    @POST("Bank_proof_uploading")
    Call<ResponseAccountProof>Accountproofupload(@Part("C_ACCOUNT_PROOF\";filename=\"myfile.jpg\"") RequestBody file,
                                                 @Part("member_id")int userid);
    @FormUrlEncoded
    @POST("Dashboard")
    Call<ResponseDashboard>Dashboard(@Field("userid")int userid);

    @FormUrlEncoded
    @POST("inner_registration")
    Call<ResponseInnerRegistration>InnerReg(@Field("c_name")String n,
                                            @Field("c_gender")String gen,
                                            @Field("c_dob")String dob,
                                            @Field("c_address")String add,
                                            @Field("n_pincode")String zip,
                                            @Field("c_country")String con,
                                            @Field("C_STATE")String st,
                                            @Field("C_DISTRICT")String dis,
                                            @Field("C_MOBILE")String mob,
                                            @Field("c_username")String uname,
                                            @Field("c_password")String cpsd,
                                            @Field("c_email")String em,
                                            @Field("c_sponsor_name")String sponsername,
                                            @Field("c_upline")String uplineuser,
                                            @Field("c_position")String pos);


    @FormUrlEncoded
    @POST("Binary_genealogy")
    Call<ResponseGene>Gene(@Field("userid")int userid);

    @FormUrlEncoded
    @POST("Sponsors_tree")
    Call<ResponseSponsorTree>SponsorTree(@Field("id")int userid);



    @FormUrlEncoded
    @POST("New_entry")
    Call<ResponseNewEntry>NewEntry(@Field("userid")int userid);

    @FormUrlEncoded
    @POST("Strategy")
    Call<ResponseStrategy>Strategy(@Field("userid")int userid,
                                   @Field("c_strategy1")String str);
    @FormUrlEncoded
    @POST("View_strategy")
    Call<ResponseStrategyView>StrategyView(@Field("userid")int userid);

    @FormUrlEncoded
    @POST("Pin_purchase")
    Call<ResponsePinRequest>PinRequest(@Field("id")int userid,
                                       @Field("from_date")String from,
                                       @Field("to_date")String to);

    @FormUrlEncoded
    @POST("Pin_purchase")
    Call<ResponsePinSearch>PinSearch(@Field("id")int userid,
                                     @Field("from_date") String from,
                                     @Field("to_date") String to);



    @FormUrlEncoded
    @POST("Payment_view")
    Call<ResponseOtrView> OtrView(@Field("order_id")int order_id,
                                  @Field("path")String path);


    @Multipart
    @POST("Payment_view_upload")
    Call<ResponseOtrUpload>OtrUpload(@Part("c_otrfile\";filename=\"myfile.jpg\"") RequestBody file,
                                     @Part("order_id")int order_id,
                                     @Part("otr_no")int otrno);


//sponsor list
    @FormUrlEncoded
    @POST("Direct_referral_list")
    Call<ResponseSponsorSearch>SponsorListView(@Field("id")int userid,
                                                 @Field("from_date")String from,
                                                 @Field("to_date")String to);


    @FormUrlEncoded
    @POST("Myteamleft_search_list")
    Call<ResponseSponsorSearch>LeftMembers(@Field("id")int userid,
                                         @Field("from_date")String from,
                                         @Field("to_date")String to,
                                           @Field("side")String side);

    @FormUrlEncoded
    @POST("Myteamright_search_list")
    Call<ResponseSponsorSearch>RightMembers(@Field("id")int userid,
                                          @Field("from_date")String from,
                                          @Field("to_date")String to,
                                            @Field("side")String side);



    @FormUrlEncoded
    @POST("Mysalesleft_search_list")
    Call<ResponseSales>LeftSales(@Field("id")int userid,
                                 @Field("from_date")String from,
                                 @Field("to_date")String to,
                                 @Field("side")String side);

    @FormUrlEncoded
    @POST("Mysalesright_search_list")
    Call<ResponseSales>RightSales(@Field("id")int userid,
                                         @Field("from_date")String from,
                                         @Field("to_date")String to,
                                          @Field("side")String side);




//    @FormUrlEncoded
//    @POST("search_sponsor_list")
//    Call<ResponseSponsorSearch>SponsorSearch(@Field("userid")int userid,
//                                             @Field("from") String from,
//                                             @Field("to") String to,
//                                             @Field("search_key")String searchkey);

//    @FormUrlEncoded
//    @POST("Channel_partner_list")
//    Call<ResponseCpOneList>CpList(@Field("userid")int userid,
//                                  @Field("from")String from,
//                                  @Field("to")String to,
//                                  @Field("side")String side);
//    @FormUrlEncoded
//    @POST("Channel_partner_sales")
//    Call<ResponseCpSales>CpSalesView(@Field("userid")int userid,
//                                 @Field("from")String from,
//                                 @Field("to")String to,
//                                 @Field("side")String side);
//    @FormUrlEncoded
//    @POST("Group_repurchase")
//    Call<ResponseGroupRepurchase>GroupRe(@Field("userid")int userid,
//                                         @Field("from")String from);
//    @FormUrlEncoded
//    @POST("Date_group_repurchase")
//    Call<ResponseGroupDate>Groupdate(@Field("userid")int userid);
//
//    @FormUrlEncoded
//    @POST("First_purchase_bv")
//    Call<ResponsePurchaseBV>FirstPurchase(@Field("userid")int userid,
//                                          @Field("from")String from,
//                                          @Field("to")String to);
//    @FormUrlEncoded
//    @POST("Repurchase_pv")
//    Call<ResponseRepurchsePV>Repurchase(@Field("userid")int userid,
//                                        @Field("from")String from,
//                                        @Field("to")String to);


    @FormUrlEncoded
    @POST("Forgot_password")
    Call<ResponseForgotPassword>ForgotPassword(@Field("c_username")String username,
                                     @Field("phone")String mobile);

    @FormUrlEncoded
    @POST("Reset_Password")
    Call<ResponseResetPassword>ResetPassword(@Field("reset_code")String otp,
                                               @Field("c_new_password")String newpassword);


    @FormUrlEncoded
    @POST("Change_password")
    Call<ResponseChangePsd>Changepsd(@Field("userid")int userid,
                                     @Field("user_password")String newpsd);

    @FormUrlEncoded
    @POST("Upgrade_BA")
    Call<ResponseUpgradeBA>UpgradeBA(@Field("userid")int userid);

    @FormUrlEncoded
    @POST("View_Upline_BA")
    Call<ResponseViewUpline>ViewUpline(@Field("userid")int userid);

    @FormUrlEncoded
    @POST("Upline_BA_post")
    Call<ResponseUplinePost>UplinePost(@Field("userid")int userid,
                                       @Field("uplineid")String uplineid);

    @FormUrlEncoded
    @POST("news")
    Call<ResponseNews>News(@Field("userid")int userid);


    Call<ResponseIncomeWallet> IncomeWallet(int parseInt);

    @FormUrlEncoded
    @POST("Profile_bank_info")
    Call<ResponseViewBankdetails> viewbankdetails(@Field("userid")int userid);

    @FormUrlEncoded
    @POST("KYC_view")
    Call<ResponseKycView> KycView(@Field("user_id")int userid);

    @FormUrlEncoded
    @POST("PAN_view")
    Call<ResponsePanView> PanView(@Field("user_id")int userid);

    @FormUrlEncoded
    @POST("Bank_view")
    Call<ResponseBankProofView> BankProofView(@Field("user_id")int userid);

    @FormUrlEncoded
    @POST("Upline_check")
    Call<ResponseUplineCheck>UplineCheck(@Field("c_upline")String c_upline);

    @FormUrlEncoded
    @POST("Sponsor_check")
    Call<ResponseSponsorCheck>SponsorCheck(@Field("c_sponsor_name")String c_upline);


    @FormUrlEncoded
    @POST("Product_details")
    Call<ResponseGetProduct> GetProduct(@Field("id")String id,
                                        @Field("ordered_date")String orderdate);

    @FormUrlEncoded
    @POST("Product_more_view")
    Call<ResponsePinProduct> PinProduct(@Field("id")String id,
                                        @Field("ordered_date")String orderdate);

    @FormUrlEncoded
    @POST("Product_more_view")
    Call<ResponsePinProduct> PinProductMoreView(@Field("order_id")String order_id);

    @FormUrlEncoded
    @POST("View_order_acceptance")
    Call<ResponseGetBill> GetBill(@Field("id")String id,
                                  @Field("d_date")String orderdate,
                                  @Field("n_order_id")String orderid);

    @FormUrlEncoded
    @POST("Ordered_product_details")
    Call<ResponseMyProduct> MyProductview(@Field("id")int parseInt,
                                          @Field("from_date")String fdate,
                                          @Field("to_date")String tdate);

    @FormUrlEncoded
    @POST("Direct_sales_bonus_list")
    Call<ResponseDirectSales> DirectSales(@Field("id")int u,
                                          @Field("from_date")String fdate,
                                          @Field("to_date")String tdate);
    @FormUrlEncoded
    @POST("Direct_sales_bonus_load")
    Call<ResponseDirectSalesLoad> DirectSalesLoad(@Field("id")int u);


    @FormUrlEncoded
    @POST("Search_leader_success_bonus")
    Call<ResponseLsbsSearch> LsbSearch(@Field("id")int id,
                                       @Field("from_date")String fdate,
                                       @Field("to_date")String tdate);

    @FormUrlEncoded
    @POST("Search_leader_success_bonus")
    Call<ResponseLsbsSearch> LsbFirstSearch(@Field("id")int id);


    @FormUrlEncoded
    @POST("Leader_level_bv_search")
    Call<ResponseLeaderLevelBv> LeaderLevelBV(@Field("id")int id,
                                              @Field("from_date")String fdate,
                                              @Field("to_date")String tdate);

    @FormUrlEncoded
    @POST("First_purchase_search_details")
    Call<ResponseFirstPurchaseSearch> FirstPurchase(@Field("id")int parseInt,
                                                    @Field("from_date")String fdate,
                                                    @Field("to_date")String tdate);
    @FormUrlEncoded
    @POST("Repurchase_search_details")
    Call<ResponseRepurchaseBvReport> RepurchaseBvReport(@Field("id")int parseInt,
                                                        @Field("from_date")String fdate,
                                                        @Field("to_date")String tdate);

    @FormUrlEncoded
    @POST("Daily_payout_list")
    Call<ResponseTeamSalesBonus> TeamSalesBonus(@Field("id")int parseInt,
                                                @Field("from_date")String fdate,
                                                @Field("to_date")String tdate);
    @FormUrlEncoded
    @POST("Daily_payout_list")
    Call<ResponseRepurchaseIncomeDetails> RepurchaseIncomeDetails(@Field("id")int parseInt,
                                                                  @Field("from_date")String fdate,
                                                                  @Field("to_date")String tdate);

    @FormUrlEncoded
    @POST("Daily_payout_load")
    Call<ResponseRepurchaseIncomeDetails> AllRepurchaseIncomeDetails(@Field("id")int parseInt);

    @FormUrlEncoded
    @POST("Binary_pv_matching_search_details")
    Call<ResponseTeamSalesBvMatching> TeamSalesBv(@Field("id")int parseInt,
                                                  @Field("fromdate")String fdate,
                                                  @Field("todate")String tdate);
    @FormUrlEncoded
    @POST("Repurchase_sales_income_list")
    Call<ResponseRepurchaseIncome> RepurchaseIncome(@Field("id")int parseInt,
                                                    @Field("from_date")String fdate,
                                                    @Field("to_date")String tdate);
    @FormUrlEncoded
    @POST("Repurchase_sales_income_load")
    Call<ResponseRepurchaseIncome> AllRepurchaseIncome(@Field("id")int parseInt);


}
