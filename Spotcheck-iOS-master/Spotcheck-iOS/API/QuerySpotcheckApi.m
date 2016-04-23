
//
// QuerySpotcheckApi.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   SpotcheckApi/v1
// Description:
//   This is an API
// Classes:
//   QuerySpotcheckApi (3 custom class methods, 3 custom properties)

#import "QuerySpotcheckApi.h"

#import "Account.h"
#import "AccountForm.h"

@implementation QuerySpotcheckApi

@dynamic email;
@dynamic password;

#pragma mark - Service level methods
// These create a QuerySpotcheckApi object.

+ (instancetype)createAccount:(AccountForm *)accountForm
{
    NSString *methodName = @"accountApi.createAccount";
    QuerySpotcheckApi *query = [self queryWithMethodName:methodName];
    query.bodyObject = accountForm;
    query.expectedObjectClass = [Account class];
    return query;
}

+ (instancetype)authenticateAccount:(NSString *)email: (NSString *)password
{
    NSString *methodName = @"accountApi.authenticateAccount";
    QuerySpotcheckApi *query = [self queryWithMethodName:methodName];
    query.email = email;
    query.password = password;
    query.expectedObjectClass = [Account class];
    return query;
}

@end
