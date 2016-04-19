//
//  QuerySpotcheckApi.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   SpotcheckApi/v1
// Description:
//   This is an API
// Classes:
//   QuerySpotcheckApi (3 custom class methods, 3 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
#import "GTL/GTLQuery.h"
#else
#import "GTLQuery.h"
#endif

@class AccountForm;

// This interface is a GTLQuery object
// see the GTL folder for more details
@interface QueryAccountApi : GTLQuery


// Method-specific parameters; see the comments below for more information.

// @property (nonatomic, copy) AccountForm *accountForm;
@property (nonatomic, copy) NSString *email;
@property (nonatomic, copy) NSString *password;

#pragma mark - Service level methods

+ (instancetype)createAccount:(AccountForm *)accountForm;
+ (instancetype)authenticateAccount: (NSString *)email :(NSString *)password;



@end
