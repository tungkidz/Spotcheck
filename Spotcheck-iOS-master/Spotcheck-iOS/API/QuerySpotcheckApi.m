
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

@dynamic name;

#pragma mark - Service level methods
// These create a QuerySpotcheckApi object.

+ (instancetype)saveAccount:(AccountForm *)accountForm
{
    NSString *methodName = @"spotcheck.saveAccount";
    QuerySpotcheckApi *query = [self queryWithMethodName:methodName];
    query.bodyObject = accountForm;
    query.expectedObjectClass = [Account class];
    return query;
}

+ (instancetype)getAccount:(NSString *)name
{
    NSString *methodName = @"spotcheck.sayHi";
    QuerySpotcheckApi *query = [self queryWithMethodName:methodName];
    query.name = name;
    query.expectedObjectClass = [Account class];
    return query;
}

@end
