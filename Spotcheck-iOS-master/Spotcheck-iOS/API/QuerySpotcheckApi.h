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
@class SpotForm;

// This interface is a GTLQuery object
// see the GTL folder for more details
@interface QuerySpotcheckApi : GTLQuery


// Method-specific parameters; see the comments below for more information.
//
// identifier property maps to 'id' in JSON (to avoid Objective C's 'id').

// @property (nonatomic, copy) AccountForm *accountForm;
@property (nonatomic, copy) NSString *email;
@property (nonatomic, copy) NSString *password;

#pragma mark - Service level methods
// These create a GTLQuerySuggestionBeanApi object.

// Method: suggestionBeanApi.getAnswer
//  Authorization scope(s):
//   kGTLAuthScopeSuggestionBeanApiUserinfoEmail
// Fetches an Account.
+ (instancetype)createAccount:(AccountForm *)accountForm;
+ (instancetype)authenticateAccount:(NSString *)email:(NSString *)password;

+ (instancetype)createSpot:(SpotForm *)spotForm;



@end
